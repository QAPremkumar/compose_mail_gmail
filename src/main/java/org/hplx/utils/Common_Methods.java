package org.hplx.utils;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

public class Common_Methods {


    public static boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public static String[] split(String attribute) {
        return attribute.split(":");

    }

    public static String currentDateAndTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return now.format(dtf);
    }

    public static String getCurrentDateTimeCustom(String separator_Character) {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String timeStamp = formatter.format(now).replace("/", separator_Character);
        timeStamp = timeStamp.replace(" ", separator_Character);
        timeStamp = timeStamp.replace(":", separator_Character);
        return timeStamp;
    }

    public static String getNextDate(String curDate) throws ParseException {
        final SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        final Date date = format.parse(curDate);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }

    public static String getDateByDays(String dateFormat, int days) {

        String newDate = "";
        try {
            // creating the present date in java
            Date d = new Date();
            //outputting the date in user reading format.
            SimpleDateFormat dF = new SimpleDateFormat(dateFormat);
            String dS = dF.format(d);
            // creating the instance for calendar class
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            // adding 3 years, 2 months, and 1 day

            c.add(Calendar.DATE, days);
            Date d1 = c.getTime();
            // printing the new date
            newDate = dF.format(d1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static String getNextSaturday(String dateFormat) {

        String newDate = "";
        try {
            // creating the present date in java
            Date d = new Date();
            //outputting the date in user reading format.
            SimpleDateFormat dF = new SimpleDateFormat(dateFormat);
            String dS = dF.format(d);
            // creating the instance for calendar class
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            // adding 3 years, 2 months, and 1 day

            while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                c.add(Calendar.DATE, 1);
            }

            //c.add(Calendar.DATE, 0);
            Date d1 = c.getTime();


            // printing the new date
            newDate = dF.format(d1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static boolean compareDates(String format, String firstDate, String secondDate) {
        boolean areDatesCompared = false;
        try {
            SimpleDateFormat sdfo = new SimpleDateFormat(format);
            //dates to be compared
            Date date1 = sdfo.parse(firstDate);
            Date date2 = sdfo.parse(secondDate);
            // Print the dates
            System.out.println("Date1: " + sdfo.format(date1));
            System.out.println("Date2: " + sdfo.format(date2));
            //Compare the two dates
            if (date1.after(date2)) {
                //if date1>date2, prints the following statement
                System.out.println(date1 + ": Date1 comes after Date2: " + date2);
            } else if (date1.before(date2)) {
                //if date1<date2, prints the following statement
                System.out.println(date1 + ": Date1 comes before Date2: " + date2);
            } else if (date1.equals(date2)) {
                //date1=date2 prints the following statement
                System.out.println(date1 + ": Date1 equal to Date2: " + date2);
            }
            areDatesCompared = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areDatesCompared;
    }


    public static boolean isSorted(List<String> listOfStrings, String format) {
        if (listOfStrings.isEmpty() || listOfStrings.size() == 1) {
            return true;
        }


        SimpleDateFormat sdfo = new SimpleDateFormat(format);
        //dates to be compared

        Iterator<String> iter = listOfStrings.iterator();
        String current = "", previous = iter.next();

        while (iter.hasNext()) {
            current = iter.next();
            try {
                if (sdfo.parse(previous).after(sdfo.parse(current))) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            previous = current;
        }
        return true;
    }

    public static String getCurrentMonthName(String format) {
        LocalDate currentDate = LocalDate.now();

        // Format current month with full name
        DateTimeFormatter fullMonthFormatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
        String fullMonthName = currentDate.format(fullMonthFormatter);
        return fullMonthName;
    }

    public static String getCurrentYear(String format) {
        LocalDate currentDate = LocalDate.now();

        // Format current month with full name
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern(format);
        String formattedYear = currentDate.format(yearFormatter);
        return formattedYear;
    }


    public static void main(String[] args) throws ParseException {
        System.out.println("date :" + currentDateAndTime());
        System.out.println("Next Date :" + getNextDate(currentDateAndTime()));
        System.out.println("date withing 6 days " + checkDateIsWithinSixDays("dd MMM yy", "07 Apr 23"));
        System.out.println("convert date " + formatDate("07 Apr 23", "dd MMM yy", "dd MMM yyyy"));
        System.out.println("get current time " + getCurrentTime("hh:mm"));
        System.out.println("get current day " + getDateByDays("dd MMM yy", 1));
        System.out.println("get next saturday " + getNextSaturday("dd MMM yy"));
        System.out.println("check within date range" + checkDateIsWithRange("dd-MMM-yyy", "20-Jul-2023", "-30"));


        System.out.println("Full Month Name: " + getCurrentMonthName("MMMM"));
        System.out.println("Current Year: " + getCurrentYear("yy"));
        String currentMonthAndYear = Common_Methods.getCurrentMonthName("MMMM") + "-" + Common_Methods.getCurrentYear("yyyy");
        System.out.println("Current  " + currentMonthAndYear);

    }

    public static void openReports(String linkReport) {
        //  if (OPEN_REPORTS_AFTER_EXECUTION.trim().equalsIgnoreCase(YES)) {
        try {
            Desktop.getDesktop().browse(new File(linkReport).toURI());
        } catch (FileNotFoundException fileNotFoundException) {
            // throw new InvalidPathForExtentReportFileException("Extent Report file you are trying to reach is not found", fileNotFoundException.fillInStackTrace());
        } catch (IOException ioException) {
            // throw new FrameworkException("Extent Report file you are trying to reach got IOException while reading the file", ioException.fillInStackTrace());
        }
    }

    public static boolean checkDateIsWithinSixDays(String format, String appDate) {
        boolean areDatesCompared = false;
        try {
            SimpleDateFormat sdfo = new SimpleDateFormat(format);
            //dates to be compared
            Date newDate = sdfo.parse(appDate);
            Date date1 = sdfo.parse(getDateByDays(format, -6));
            Date date2 = sdfo.parse(getDateByDays(format, 0));
            // Print the dates
            //Compare the two dates
            if (newDate.after(date1) && newDate.before(date2)) {
                //if date1>date2, prints the following statement
                System.out.println(date1 + ": Date1 comes after Date2: " + date2);
                areDatesCompared = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areDatesCompared;
    }

    public static String getCurrentTime(String timeFormat) {
        LocalTime time = LocalTime.now();
        String t = time.format(DateTimeFormatter.ofPattern(timeFormat));
        System.out.println(t);
        return t;
    }


    public static String formatDate(String date, String inputFormat, String outputFormat) {

        String newDate;
        DateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        DateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        try {
            newDate = outputDateFormat.format((inputDateFormat.parse(date)));
        } catch (Exception e) {
            newDate = "";
        }
        return newDate;

    }


    public static boolean checkDateIsWithRange(String format, String appDate, String days) {
        boolean areDatesCompared = false;
        try {
            SimpleDateFormat sdfo = new SimpleDateFormat(format);
            //dates to be compared
            Date newDate = sdfo.parse(appDate);
            Date date1 = sdfo.parse(getDateByDays(format, Integer.parseInt(days)));
            Date date2 = sdfo.parse(getDateByDays(format, 0));
            // Print the dates
            //Compare the two dates
            if (newDate.after(date1) && newDate.before(date2)) {
                //if date1>date2, prints the following statement
                System.out.println(date1 + ": Date1 comes after Date2: " + date2);
                areDatesCompared = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areDatesCompared;
    }
}
