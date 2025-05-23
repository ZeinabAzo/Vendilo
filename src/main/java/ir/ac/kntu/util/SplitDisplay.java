package ir.ac.kntu.util;

import java.util.ArrayList;
import java.util.List;

public class SplitDisplay {

    public static int show(List<?> list){

        int currentState=0;
        int k=1;

        for(int i=0; i< list.size(); i+=10*k){

            List<?> tempList= new ArrayList<>(list.subList(i, i+10));

            if (list.size()-i<=10) currentState=2;
            else if(i<10) currentState=0;
            else currentState=1;

            fancyPrint(tempList, currentState);

            int choice=ScannerWrapper.nextInt();
            switch (choice){
                case 1 -> {
                    k = 1;
                }
                case 2 -> k=-1;
                case 3 -> {
                    return -1;
                }
                default -> k=0;
            }
        }
        return 0;//delete this
    }

    private static void fancyPrint(List<?> tempList, int currentState){
        // 1-next 2-previous 3-exit 4-choose(int)
    }
}
