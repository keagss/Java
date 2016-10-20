
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.util.Animator;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.functors.ConstantTransformer;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.PolarPoint;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.*;

/**This class is
 * used to show the radial tree
 */

public class radialTree extends JApplet {

    //graph
    Forest<String,Integer> graph;



	Factory<Tree<String,Integer>> treeFactory =
		new Factory<Tree<String,Integer>> () {

		public Tree<String, Integer> create() {
			return new DelegateTree<String,Integer>(graphFactory);
		}
	};

	Factory<DirectedGraph<String,Integer>> graphFactory =
			new Factory<DirectedGraph<String,Integer>>() {

				public DirectedGraph<String, Integer> create() {
					return new DirectedSparseMultigraph<String,Integer>();
				}
			};

	Factory<Integer> edgeFactory = new Factory<Integer>() {
		int i=0;
		public Integer create() {
			return i++;
		}};

	//renderer of graph
    VisualizationViewer<String,Integer> visViewer;

    VisualizationServer.Paintable rings;


    TreeLayout<String,Integer> treeLayout;

    RadialTreeLayout<String,Integer> radialLayout;

    public radialTree() {


        graph = new DelegateForest<String,Integer>();

        createTree();

 		//sets layout for the radial tree
        radialLayout = new RadialTreeLayout<String,Integer>(graph);
        radialLayout.setSize(new Dimension(700,700));
		treeLayout = new TreeLayout<String,Integer>(graph);

		//sets size,labels and background colour
        visViewer =  new VisualizationViewer<String,Integer>(treeLayout, new Dimension(700,900));
        visViewer.setBackground(Color.white);
		visViewer.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        visViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());


        visViewer.setVertexToolTipTransformer(new ToStringLabeller());
        visViewer.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
		//rings for radial view
        rings = new Rings();

        setLtoR(visViewer);

        Container content = getContentPane();
		//adds scroll zzom
        final GraphZoomScrollPane panel = new GraphZoomScrollPane(visViewer);
        content.add(panel);

        final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

        visViewer.setGraphMouse(graphMouse);

        JComboBox modeBox = graphMouse.getModeComboBox();
        modeBox.addItemListener(graphMouse.getModeListener());
        graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);

        final ScalingControl scaler = new CrossoverScalingControl();

		//sets the zoom in and out button
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(visViewer, 1.1f, visViewer.getCenter());
            }
        });
        JButton minus = new JButton("-");
        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scaler.scale(visViewer, 1/1.1f, visViewer.getCenter());
            }
        });

        JToggleButton radial = new JToggleButton("Radial View");
        radial.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {

					LayoutTransition<String,Integer> lt =
						new LayoutTransition<String,Integer>(visViewer, treeLayout, radialLayout);
					Animator animator = new Animator(lt);
					animator.start();
					visViewer.getRenderContext().getMultiLayerTransformer().setToIdentity();
					visViewer.addPreRenderPaintable(rings);
				} else {
					LayoutTransition<String,Integer> lt =
						new LayoutTransition<String,Integer>(visViewer, radialLayout, treeLayout);
					Animator animator = new Animator(lt);
					animator.start();
					visViewer.getRenderContext().getMultiLayerTransformer().setToIdentity();
					setLtoR(visViewer);
					visViewer.removePreRenderPaintable(rings);
				}

				visViewer.repaint();
			}});

        JPanel scaleGrid = new JPanel(new GridLayout(1,0));
        scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

        JPanel controls = new JPanel();
        scaleGrid.add(plus);
        scaleGrid.add(minus);
        controls.add(radial);
        controls.add(scaleGrid);
        controls.add(modeBox);

        content.add(controls, BorderLayout.SOUTH);
    }

    private void setLtoR(VisualizationViewer<String,Integer> vv) {
    	Layout<String,Integer> layout = vv.getModel().getGraphLayout();
    	Dimension d = layout.getSize();
    	Point2D center = new Point2D.Double(d.width/2, d.height/2);
    	vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).rotate(-Math.PI/2, center);
    }

    class Rings implements VisualizationServer.Paintable {

    	Collection<Double> depths;

    	public Rings() {
    		depths = getDepths();
    	}


    	private Collection<Double> getDepths() {
    		Set<Double> depths = new HashSet<Double>();
    		Map<String,PolarPoint> polarLocations = radialLayout.getPolarLocations();
    		for(String v : graph.getVertices()) {
    			PolarPoint pp = polarLocations.get(v);
    			depths.add(pp.getRadius());
    		}
    		return depths;
    	}
		//sets size of rings
		public void paint(Graphics g) {
			g.setColor(Color.lightGray);

			Graphics2D g2d = (Graphics2D)g;
			Point2D center = radialLayout.getCenter();

			Ellipse2D ellipse = new Ellipse2D.Double();
			for(double d : depths) {
				ellipse.setFrameFromDiagonal(center.getX()-d, center.getY()-d,
						center.getX()+d, center.getY()+d);
				Shape shape = visViewer.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).transform(ellipse);
				g2d.draw(shape);
			}
		}

		public boolean useTransform() {
			return true;
		}
    }

    /**Creates the tree by comparing the original and newcells
	 * and creating a directed line from the original cells to the new cells
     */
    private void createTree() {
		Main listClass = new Main();
		listClass.setVisible(false);

		ArrayList<String> originalCells = listClass.getList();
		ArrayList<String> newCells = listClass.getList2();


		int k =0;
		int j =0;

		for (int i=0; i < originalCells.size();) {
			graph.addVertex(originalCells.get(i));

			i++;

			while (newCells.size() > j && originalCells.get(k).equals(newCells.get(j).split("\\.", 2)[0])) {

				graph.addEdge(edgeFactory.create(), originalCells.get(k), newCells.get(j));
				j++;


			}
			k++;
		}
	}


}

