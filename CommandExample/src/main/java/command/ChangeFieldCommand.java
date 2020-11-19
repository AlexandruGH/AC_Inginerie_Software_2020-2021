package command;

import model.SampleModel;

public class ChangeFieldCommand implements Command{
    private final String text;
    private final SampleModel model;


    public ChangeFieldCommand(String text, SampleModel sampleModel){
        this.text=text;
        this.model=sampleModel;
    }

    @Override
    public void execute() {
        model.setField(text);
    }
}
