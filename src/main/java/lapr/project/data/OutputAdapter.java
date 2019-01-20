package lapr.project.data;

import lapr.project.model.Bike;
import lapr.project.model.BikeType;

import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

public class OutputAdapter {

    public OutputAdapter() {

    }

    /**
     * Write Custom Data to File
     */
    public void writeDataToFile(String filePath, List<String> linesData) {
        try (PrintWriter printWriter = new PrintWriter(filePath, "UTF-8")) {
            boolean f = false;
            for (String lineString : linesData) {
                if (f) {
                    printWriter.println();

                }
                f = true;
                printWriter.print(lineString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bikes
     */
    public void writeBikesToFile(String filePath, List<Bike> bikeList) {
        try (PrintWriter printWriter = new PrintWriter(filePath, "UTF-8")) {
            boolean f = false;
            for (Bike bike : bikeList) {
                if (f) {
                    printWriter.println();

                }
                f = true;
                String description = bike.getDescription();
                BikeType bikeType = bike.getType();
                String type = "road";
                if (bikeType == BikeType.ELECTRICAL) {
                    type = "electric";
                } else if (bikeType == BikeType.MOUNTAIN) {
                    type = "mtb";
                }
                Double currentBattery = bike.getCurrentBattery();
                Double maxBattery = bike.getMaxBattery();
                double percentage = 0.0;
                if (!Objects.equals(maxBattery, 0.0)) {
                    percentage = (currentBattery / maxBattery) * 100;
                }
                String percentageWithoutDecimals = String.format("%.0f", percentage);
                printWriter.print(description + ";" + type + ";" + percentageWithoutDecimals);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
