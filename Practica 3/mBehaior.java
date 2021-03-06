
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.AWTEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.media.j3d.*;
import javax.vecmath.*;

public class mBehaior extends Behavior{

	TransformGroup Camara;
	TransformGroup Node;
	/*WakeupOnAWTEvent informa al programador Java 3D para activar 
	 *el objeto Behavior especificado cuando se produce el evento AWT especificado.*/
	private WakeupOnAWTEvent trigger;
	Integer Zoom=3;
	
    public mBehaior(SimpleUniverse su, TransformGroup Node) {
    	this.Node=Node;
    	this.Camara=su.getViewingPlatform().getViewPlatformTransform();
    	trigger=new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED);
    	Posicion();
    	setSchedulingBounds(new BoundingSphere( new Point3d( 0.0, 0.0, 0.0 ), 500.0 ));
    }
    private void Posicion (){
    	Node.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D tr1 = new Transform3D();
		tr1.set(new Vector3d(0f, 0.9f, +Zoom));
		Camara.setTransform(tr1);
		tr1.set(new Vector3f(0f, 0.0f, 0f));
		Node.setTransform(tr1);	
    } 
    	public void initialize() 
	{this.wakeupOn(trigger);}

	public void processStimulus(Enumeration criteria) {
		while (criteria.hasMoreElements()) 
		{
			WakeupCriterion wakeup = (WakeupCriterion) criteria.nextElement();
			if (wakeup instanceof WakeupOnAWTEvent) {
				AWTEvent[] arr = ((WakeupOnAWTEvent) (wakeup)).getAWTEvent();
				KeyEvent ke = (KeyEvent) arr[0];
				switch (ke.getKeyCode()) {
					case KeyEvent.VK_UP:
						Arriba();
						break;
					case KeyEvent.VK_DOWN:
						Abajo();
						break;
					case KeyEvent.VK_LEFT:
						Izq(9);
						break;
					case KeyEvent.VK_RIGHT:
						Der(9);
						break;
				}
			}
		}
		wakeupOn(trigger);
	}	
	public void Arriba(){
		Transform3D tr1=new Transform3D();
		Transform3D tr2=new Transform3D();
		this.Camara.getTransform(tr2);
		Vector3f vec=new Vector3f();
		vec.z=-0.1f;
		tr1.set(vec);
		tr2.mul(tr1);
		this.Camara.setTransform(tr2);
		vec.z=-0.1f;
		this.Node.getTransform(tr2);
		tr1.set(vec);
		tr2.mul(tr1);
		Node.setTransform(tr2);
}
	public void Abajo(){
		Transform3D tr1=new Transform3D();
		Transform3D tr2=new Transform3D();
		this.Camara.getTransform(tr2);
		Vector3f vec=new Vector3f();
		vec.z=+0.1f;
		tr1.set(vec);
		tr2.mul(tr1);
		this.Camara.setTransform(tr2);
		vec.z=+0.1f;
		this.Node.getTransform(tr2);
		tr1.set(vec);
		tr2.mul(tr1);
		Node.setTransform(tr2);
	}
	public void Izq(int angulo){
		Transform3D tr1=new Transform3D();
		Transform3D tr2=new Transform3D();
		tr1.rotY(Math.PI/180*angulo);
		this.Node.getTransform(tr2);
		tr2.mul(tr1);
		this.Node.setTransform(tr2);
		Vector3f vec=new Vector3f(0f,0f,-this.Zoom);
		tr1.set(vec);
		this.Camara.getTransform(tr2);
		tr2.mul(tr1);
		tr1.rotY(Math.PI/180*angulo);
		tr2.mul(tr1);
		vec=new Vector3f(0f,0f,this.Zoom);
		tr1.set(vec);
		tr2.mul(tr1);
		Camara.setTransform(tr2);
	}
	public void Der(int angulo){
		Transform3D tr1=new Transform3D();
		Transform3D tr2=new Transform3D();
		tr1.rotY(-Math.PI/180*angulo);
		this.Node.getTransform(tr2);
		tr2.mul(tr1);
		this.Node.setTransform(tr2);
		Vector3f vec=new Vector3f(0f,0f,-this.Zoom);
		tr1.set(vec);
		this.Camara.getTransform(tr2);
		tr2.mul(tr1);
		tr1.rotY(-Math.PI/180*angulo);
		tr2.mul(tr1);
		vec=new Vector3f(0f,0f,this.Zoom);
		tr1.set(vec);
		tr2.mul(tr1);
		Camara.setTransform(tr2);
	}
}
