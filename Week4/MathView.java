package com.example.week4;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@Route("index1")
public class MathView extends VerticalLayout {
    private TextField number1, number2, answer;
    private Button plusBtn, minusBtn, divideBtn, multiBtn, modBtn, maxBtn;
    private HorizontalLayout layoutBtn;
    private Label text;

    public MathView(){

        number1 = new TextField("Number1");
        number2 = new TextField("Number2");
        answer = new TextField("Answer");
        plusBtn = new Button("+");
        plusBtn.addClickListener(event ->{
           double num1 = Double.parseDouble(number1.getValue());
           double num2 = Double.parseDouble(number2.getValue());

           String out = WebClient
                   .create()
                   .get()
                   .uri("http://localhost:8080/plus/" + num1 + "/" + num2)
                   .retrieve()
                   .bodyToMono(String.class)
                   .block();

           answer.setValue(out);
        });
        minusBtn = new Button("-");
        minusBtn.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());

            String out = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/minus/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            answer.setValue(out);
        });
        divideBtn = new Button("/");
        divideBtn.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());

            String out = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/divide/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            answer.setValue(out);
        });
        multiBtn = new Button("x");
        multiBtn.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());

            String out = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/multi/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            answer.setValue(out);
        });
        modBtn = new Button("Mod");
        modBtn.addClickListener(event ->{
            double num1 = Double.parseDouble(number1.getValue());
            double num2 = Double.parseDouble(number2.getValue());

            String out = WebClient
                    .create()
                    .get()
                    .uri("http://localhost:8080/mod/" + num1 + "/" + num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            answer.setValue(out);
        });
        maxBtn = new Button("Max");
        maxBtn.addClickListener(event ->{
            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("n1", number1.getValue());
            formData.add("n2", number2.getValue());

            String out = WebClient
                    .create()
                    .post()
                    .uri("http://localhost:8080/max")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            answer.setValue(out);
        });
        text = new Label("Operator");
        layoutBtn = new HorizontalLayout();
        layoutBtn.add(plusBtn, minusBtn, multiBtn, divideBtn, modBtn, maxBtn);

        this.add(number1, number2, text, layoutBtn, answer);

    }



}
