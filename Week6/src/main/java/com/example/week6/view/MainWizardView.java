package com.example.week6.view;

import com.example.week6.pojo.Wizard;
import com.example.week6.pojo.Wizards;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;


@Route(value="/mainPage.it")
public class MainWizardView extends VerticalLayout {
    private TextField tf;
    private NumberField nf;
    private RadioButtonGroup rbtn;
    private ComboBox positionBox, schoolBox, houseBox;
    private HorizontalLayout layout;
    private Button previousBtn, createBtn, updateBtn, deleteBtn, nextBtn;
    private Wizards wizards;
    private int index = -1;

    public MainWizardView() {
        this.wizards = new Wizards();

        this.tf = new TextField();
        this.tf.setPlaceholder("Fullname");
        this.rbtn = new RadioButtonGroup("Gender :");
        this.rbtn.setItems("Male", "Female");
        this.positionBox = new ComboBox();
        this.positionBox.setPlaceholder("Position");
        this.positionBox.setItems("Student", "Teacher");
        this.nf = new NumberField("Dollars");
        this.nf.setPrefixComponent(new Paragraph("$"));
        this.schoolBox = new ComboBox();
        this.schoolBox.setPlaceholder("School");
        this.schoolBox.setItems("Hogwarts", "Beauxbatons", "Durmstrang");
        this.houseBox = new ComboBox();
        this.houseBox.setPlaceholder("House");
        this.houseBox.setItems("Gryffindor", "Ravenclaw", "Hufflepuff", "Slytherin");
        this.layout = new HorizontalLayout();
        this.previousBtn = new Button("<<");
        this.previousBtn.addClickListener(event -> {
            if (this.index >= 1){
                this.index -= 1;
                this.callWizard();
            }else if (this.index <= 0){
                this.index = 0;
            }
        });
        this.createBtn = new Button("Create");
        this.createBtn.addClickListener(event -> {
            String fullName = this.tf.getValue();
            String sex = this.rbtn.getValue().toString();
            char gender;
            if(sex.equals("Male")){
                gender = 'm';
            }
            else{
                gender = 'f';
            }
            String position = this.positionBox.getValue().toString();
            String p;
            if(position.equals("Student")){
                p = "student";
            }
            else{
                p = "teacher";
            }
            int money = this.nf.getValue().intValue();
            String school = this.schoolBox.getValue().toString();
            String house = this.houseBox.getValue().toString();

            Wizard w = new Wizard(null, fullName, gender, money, school, p, house);

            WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/addWizard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(w)
                    .retrieve()
                    .bodyToMono(Wizard.class)
                    .block();


                new Notification("Wizard has been Created", 1000).open();
                receiveWizard();
                this.index += 1;
                this.callWizard();

        });
        this.updateBtn = new Button("Update");
        this.updateBtn.addClickListener(event -> {
            String fullName = this.tf.getValue();
            String sex = this.rbtn.getValue().toString();
            char gender;
            if(sex.equals("Male")){
                gender = 'm';
            }
            else{
                gender = 'f';
            }
            String position = this.positionBox.getValue().toString();
            String p;
            if(position.equals("Student")){
                p = "student";
            }
            else{
                p = "teacher";
            }
            int money = this.nf.getValue().intValue();
            String school = this.schoolBox.getValue().toString();
            String house = this.houseBox.getValue().toString();

            Wizard w = new Wizard(this.wizards.getModel().get(this.index).get_id(), fullName, gender, money, school, p, house);

            WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/updateWizard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(w)
                    .retrieve()
                    .bodyToMono(Wizard.class)
                    .block();

            new Notification("Wizard has been Updated", 1000).open();
            this.receiveWizard();
        });
        this.deleteBtn = new Button("Delete");
        this.deleteBtn.addClickListener(event -> {
            Wizard w = this.wizards.getModel().get(this.index);
            WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/deleteWizard")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(w)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            new Notification("Wizard has been Removed", 1000).open();
            this.index -= 1;
            this.receiveWizard();
            this.callWizard();
        });
        this.nextBtn = new Button(">>");
        this.nextBtn.addClickListener(event -> {
            this.index += 1;
            if (this.index >= this.wizards.getModel().size()-1){
                this.index = this.wizards.getModel().size()-1;
            }

            this.callWizard();
        });

        this.layout.add(previousBtn, createBtn, updateBtn, deleteBtn, nextBtn);

        this.add(tf, rbtn, positionBox, nf, schoolBox, houseBox, layout);

        this.receiveWizard();

    }
    private void receiveWizard() {
        ArrayList<Wizard> w = WebClient
                .create()
                .get()
                .uri("http://localhost:8080/wizards")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ArrayList<Wizard>>() {})
                .block();

        this.wizards.setModel(w);
    };

    private void callWizard(){
        Wizard wizards =  this.wizards.getModel().get(this.index);
        this.tf.setValue(wizards.getName());
        if (wizards.getSex() == 'm'){
            rbtn.setValue("Male");
        }else{
            rbtn.setValue("Female");
        }

        if (wizards.getPosition().equals("student")){
            positionBox.setValue("Student");
        }
        else{
            positionBox.setValue("Teacher");
        }

        nf.setValue(Double.valueOf(wizards.getMoney()));
        schoolBox.setValue(wizards.getSchool());
        houseBox.setValue(wizards.getHouse());
    }

}
