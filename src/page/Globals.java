package page;

public class Globals {
    public static String chromeDriverName="webdriver.chrome.driver";
    public static String chromeDriverPath="webdriver\\chromedriver.exe";

    public static int[] getUnrepeatedRandomItemIndex(int numberOfItem, int min, int max){
        //This method is to generate {{numberOfItem}} random unrepeated integers from 1 to total listed item
        //Find the total of displayed item by counting how many elements are having 'div' tag, 'class' attribute, and 'inventory_item' attribute value
        int[] result=new int[numberOfItem];

        //Generate {numberOfItem}} random unrepeated integers
        for (int i=0; i<numberOfItem;i++){
            boolean numberFound=false;
            while (!numberFound){
                boolean isRepeated=false;
                int selectedItemIndex=0;
                selectedItemIndex=(int)((Math.random()*max)+min);
                for (int j=0; j<i;j++){
                    if(selectedItemIndex==result[j]){
                        isRepeated=true;
                    }
                }
                if (!isRepeated){
                    result[i]=selectedItemIndex;
                    numberFound=true;
                }
            }

        }
        return result;
    }
}
