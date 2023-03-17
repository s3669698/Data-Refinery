import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main
{
    public static void DataLoad(String Keyword)
    {
        String line;
        int row = 0;
        int i = 0;
        int date = 0;
        int datecompare = 0;
        Date day1;
        Date day2;
        String[] temp = new String[8];
        String path = "E:\\info.csv"; //Put the location of covid-data.csv //
        String[] index = new String[110000];
        SimpleDateFormat SDF = new SimpleDateFormat("MM/dd/yyyy");
        try
        {
            day1 = SDF.parse("1/1/2026");
            day2 = SDF.parse("1/1/2025");
            BufferedReader read = new BufferedReader(new FileReader(path));
            while((line = read.readLine()) != null)
            {
                index[row] = line;
                if(index[row].contains(Keyword))
                {
                    System.out.println(index[row]);
                    date++;
                    temp = line.split(",");
                    i = 0;
                    while(i<8)
                    {
                        if(temp[i].contains("/2020")||temp[i].contains("/2021"))
                        {
                            datecompare = day1.compareTo(day2);
                            if(datecompare > 0)
                            {
                                day1 = SDF.parse(temp[i]);
                            }
                            else
                            {
                                day2 = SDF.parse(temp[i]);
                            }
                        }
                        i++;
                    }
                }
                row++;
            }
            System.out.print("\n");
            System.out.println("Start date: " + day1);
            System.out.println("End date: " + day2);
            System.out.println("Number of days: " + (date) + " days");

            read.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main (String args[]) {

        System.out.println("Type a keyword you would like to search.");
        Scanner sc = new Scanner(System.in);
        String Keyword = sc.next();
        sc.close();
        DataLoad(Keyword);
        Frame frame = new Frame();
    }

}
