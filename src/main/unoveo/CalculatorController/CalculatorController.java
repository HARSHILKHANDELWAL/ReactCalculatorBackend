package CalculatorController;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletResponse;
import static jakarta.servlet.http.HttpServletResponse.*;

@WebServlet("/CalculatorController")

public class  CalculatorController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json");
//        System.out.println(request.getReader());
//        Gson gson1= new Gson();

        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = "";
        Result result = new Result();
        boolean flag = false;
        ArthmeticEvaluator ae = new ArthmeticEvaluator();
//        int i = 0;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        System.out.println(sb+"33");
        Gson gson1 = new Gson();

        Result[] ans1 = gson1.fromJson(sb.toString(), Result[].class);

        System.out.println(ans1[0].getType());
        System.out.println(ans1.length);

        String[] resultArrayAsArray = new String[0];
        StringBuilder finalString = null;
        try {
            // Parse the JSON string into a JsonNode
            // Extract the 'value' property from each object and form a list
            List<String> resultArray = new ArrayList<>();

            for(int i=0;i<ans1.length;i++)  {
                System.out.println(ans1[i]);
                String type1=ans1[i].getType();

//                System.out.println(type1+" type1");


                if (type1.equals("NUMBER")) {
                        if (ae.isInRange(-100000, 100000, ans1[i].getValue())){
                            resultArray.add(ans1[i].getValue());
                        } else {
                          flag=true;
                        }

                } else if (type1.equals("OPERATOR")) {

                    String operation = ans1[i].getValue();
                    if (operation.equals("Addition") || operation.equals("Subtraction") || operation.equals("Multiply") || operation.equals("Divide")) {
                        resultArray.add(ans1[i].getValue());
                    } else {
                        flag = true;
                    }
                }
            }

            // Convert the list to an array if needed
            resultArrayAsArray = resultArray.toArray(new String[0]);
            finalString = new StringBuilder();
            // Print the result

            for (String value : resultArrayAsArray) {
                String ans = null;
                System.out.println(value.getClass().getSimpleName());

                if (value.equals("Addition"))
                    ans = value.replace("Addition", "+");
                if (value.equals("Subtraction"))
                    ans = value.replace("Subtraction", "-");
                if (value.equals("Divide"))
                    ans = value.replace("Divide", "/");
                if (value.equals("Multiply"))
                    ans = value.replace("Multiply", "x");

                if (ans != null) {
                    finalString.append(ans);
                    ans = null;
                } else
                    finalString.append(value);
            }


        } catch (Exception e) {
            response.setStatus(422);
            response.getWriter().write("error occurred while processing request data");
        }
        String finalResult = String.valueOf(ae.calculate(String.valueOf(finalString)));
        Gson gson = new GsonBuilder().create();

//        result.setType("NUMBER");
        if (flag)
        {
            response.setStatus(SC_BAD_REQUEST);
//            result.setType("Please Check all the given values are correct or not");
            response.getWriter().write("Please Check all the given values are correct or not");
        }
        else
            result.setValue(finalResult);

        String data = gson.toJson(result);
        System.out.println("Result: " + finalResult);
        response.getWriter().write(data);

    }


}


//        ExpressionEvaluation ee = new ExpressionEvaluation();
//        String finalResult = String.valueOf(ae.evaluateArithmeticExpression(finalString.toString()));


//            System.out.println(Arrays.toString(resultArrayAsArray));
//            System.out.println(finalString);

