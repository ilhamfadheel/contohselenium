import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import page.Globals;
import test.AddItem;
import test.Login;

public class App {
    private static Result result;
    public static void main(String[] args){
      System.setProperty(Globals.chromeDriverName, Globals.chromeDriverPath);
      runTests();
      reportTestResult();     
    }

    private static void runTests(){
      //Run the test
      JUnitCore junit = new JUnitCore();
      result= junit.run(
                Login.class, 
                AddItem.class
          );
    }

    private static void reportTestResult(){
      //Report the tests to result/result_{date}.txt
      try{
        FileWriter myWriter = new FileWriter("result\\result_"+LocalDate.now()+".txt");
        
        //Write Result
        //If successful
        if(result.wasSuccessful()){
          myWriter.write("All tests in are PASSED");
        //If failed report the failures
        }else{
          myWriter.write("There are "+result.getFailureCount()+" FAILED test(s): \n");
          int index=1;
          for (Failure failure : result.getFailures()) {
            myWriter.write("\t"+(index++)+". "+failure.toString());
            myWriter.write("\n");
          }
        } 
        myWriter.close();
        System.out.println("Successfully wrote Result file.");

      }catch (IOException e) {
        System.out.println("An error occurred while write on Result file.");
        e.printStackTrace();
      } 
    }
}