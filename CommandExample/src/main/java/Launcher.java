import controller.SampleController;
import model.SampleModel;
import view.SampleView;

public class Launcher {
    public static void main(String []args){
        new SampleController(new SampleView(), new SampleModel());
    }
}
