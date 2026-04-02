public class EnemyControl {
    private int ballCount;
    private double radius;
    private double lowerVelocity = 0.005;
    private double upperVelocity = 0.01;
    private double[] ballsXLocation = new double[ballCount];
    private double[] ballsYLocation = new double[ballCount];
    private double[] ballsXVelocites = new double[ballCount];
    private double[] ballsYVelocites = new double[ballCount];
    
    public EnemyControl(int ballCount, double radius, double lowerVelocity, double upperVelocity, double[] ballsXLocation, double[] ballsYLocation, double[] ballsXVelocites, double[] ballsYVelocites){
        this.ballCount = ballCount;
        this.radius = radius;
        this.lowerVelocity = lowerVelocity;
        this.upperVelocity = upperVelocity;
        this.ballsXLocation = ballsXLocation;
        this.ballsYLocation = ballsYLocation;
        this.ballsXVelocites = ballsXVelocites;
        this.ballsYVelocites = ballsYVelocites;
    }

    public void setInitialBalls(){
        for(int i = 0; i < ballCount; i++) {
			ballsXLocation[i] = Math.random();
			ballsYLocation[i] = Math.random();
			ballsXVelocites[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
			ballsYVelocites[i] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
		}
    }

    public void updateBallLocation(int i){
        ballsXLocation[i] = ballsXLocation[i] + ballsXVelocites[i];
        ballsYLocation[i] = ballsYLocation[i] + ballsYVelocites[i];
        if(ballsXLocation[i] + radius > 1 || ballsXLocation[i] - radius < 0) { 
            ballsXVelocites[i] = -ballsXVelocites[i];
        }
        if(ballsYLocation[i] + radius > 1 || ballsYLocation[i] - radius < 0) { 
            ballsYVelocites[i] = -ballsYVelocites[i];
        }
        for(int j = 0; j < ballCount; j++) {
            if(i != j) {
                double d = Math.sqrt(Math.pow(ballsXLocation[i] - ballsXLocation[j], 2) + Math.pow(ballsYLocation[i] - ballsYLocation[j], 2));
                if(d < 2 * radius) {
                    ballsXVelocites[i] = -ballsXVelocites[i];
                    ballsYVelocites[i] = -ballsYVelocites[i];
                }
            }
        }
    }

    public void addBalls(){
        ballCount++;
        double[] ballXnew = new double[ballCount];
        double[] ballYnew = new double[ballCount];
        double[] ballXVnew = new double[ballCount];
        double[] ballYVnew = new double[ballCount];
        for(int i = 0; i < ballCount - 1; i++) {
            ballXnew[i] = ballsXLocation[i];
            ballYnew[i] = ballsYLocation[i];
            ballXVnew[i] = ballsXVelocites[i];
            ballYVnew[i] = ballsYVelocites[i];
        }
        ballXnew[ballCount-1] = Math.random();
        ballYnew[ballCount-1] = Math.random();
        ballXVnew[ballCount-1] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
        ballYVnew[ballCount-1] = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
        ballsXLocation = ballXnew;
        ballsYLocation = ballYnew;
        ballsXVelocites = ballXVnew;
        ballsYVelocites = ballYVnew;
    }

    public int getBallCount(){
        return this.ballCount;
    }
    
    public double getBallX(int i){
        return this.ballsXLocation[i];
    }

    public double getBallY(int i){
        return this.ballsYLocation[i];
    }

    public void setBallX(int i, double value){
        this.ballsXLocation[i] = value;
    }

    public void setBallY(int i, double value){
        this.ballsYLocation[i] = value;
    }

    public void setBallXVelocity(int i, double value){
        this.ballsXVelocites[i] = value;
    }
    public void setBallYVelocity(int i, double value){
        this.ballsYVelocites[i] = value;
    }

    public double getUpperVelocity(){
        return this.upperVelocity;
    }

    public double getLowerVelocity(){
        return this.lowerVelocity;
    }

    public void reset(int count) {
        ballCount = count;
        ballsXLocation = new double[ballCount];
        ballsYLocation = new double[ballCount];
        ballsXVelocites = new double[ballCount];
        ballsYVelocites = new double[ballCount];
        setInitialBalls();
    }
}


