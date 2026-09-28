public class TutorialController extends Controller {

    public SpringObject object;

    ComposedSpringObject cso;

    /* These are the agents senses (inputs) */
	DoubleFeature x; /* Positions */
	DoubleFeature y;
	DoubleFeature vx; /* Velocities */
	DoubleFeature vy;
	DoubleFeature angle; /* Angle */
	int i;

    /* Example:
     * x.getValue() returns the vertical position of the rocket 
     */

	/* These are the agents actuators (outputs)*/
	RocketEngine leftRocket;
	RocketEngine middleRocket;
	RocketEngine rightRocket;

    /* Example:
     * leftRocket.setBursting(true) turns on the left rocket 
     */
	
	public void init() {
		cso = (ComposedSpringObject) object;
		x = (DoubleFeature) cso.getObjectById("x");
		y = (DoubleFeature) cso.getObjectById("y");
		vx = (DoubleFeature) cso.getObjectById("vx");
		vy = (DoubleFeature) cso.getObjectById("vy");
		angle = (DoubleFeature) cso.getObjectById("angle");
		i = 0;

		leftRocket = (RocketEngine) cso.getObjectById("rocket_engine_left");
		rightRocket = (RocketEngine) cso.getObjectById("rocket_engine_right");
		middleRocket = (RocketEngine) cso.getObjectById("rocket_engine_middle");
	}

	double max_x = 0;
	double max_y = 0;
	double min_x = 0;
	double min_y = 0;
    public void tick(int currentTime) {
//    	System.out.println(String.format("Angle: %.3f VX: %.3f VY: %.3f, X: %.3f, Y: %.3f",
//    			angle.getValue(), vx.getValue(), vy.getValue(), x.getValue(), y.getValue()));
    	if (vx.getValue() > max_x)
    		max_x = vx.getValue();
    	if (vx.getValue() < min_x)
    		min_x = vx.getValue();
    	if (vy.getValue() > max_y)
    		max_y = vy.getValue();
    	if (vy.getValue() < min_y)
    		min_y = vy.getValue();
    	System.out.println(min_x + " " + max_x + " | " + min_y + max_y);
    	if (y.getValue() > 0)
    		middleRocket.setBursting(true);
    	else
    		middleRocket.setBursting(false);
    	
    	if (x.getValue() < 0 && angle.getValue() < 0.01 && i % 5 == 0) 
    		leftRocket.setBursting(true);
    	else
    		leftRocket.setBursting(false);
    	
    	if (x.getValue() > 0 && angle.getValue() > -0.01 && i % 5 == 0) 
    		rightRocket.setBursting(true);
    	else
    		rightRocket.setBursting(false);
    	i++;
    }
}
