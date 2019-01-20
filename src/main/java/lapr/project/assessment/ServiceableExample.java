/**
 *
 */
package lapr.project.assessment;

/**
 * Interface for testing administrator requirements.
 */
public interface ServiceableExample {
    /**
     * Add Bicycles to the system.
     *
     * Basic: Add one bicycle to one park.
     * Intermediate: Add several bicycles to one park.
     * Advanced: Add several bicycles to several parks.
     *
     * @param inputBicycleFile Path to file with bicycles to add, according
     *                         to input/bicycles.csv.
     * @return Number of added bicycles.
     */
    int addBicycles(String inputBicycleFile);

    /**
     * Add Parks to the system.
     *
     * Basic: Add one Park.
     * Intermediate: Add several Parks.
     *
     * @param inputParksFile Path to file that contains the parks, according
     *                       to file input/parks.csv.
     * @return The number of added parks.
     */
    int addParks(String inputParksFile);

    /**
     * Add POIs to the system.
     *
     * Basic: Add one POI.
     * Intermediate: Add several POIs.
     *
     * @param inputPOIsFile Path to file that contains the POIs, according to
     *                     file input/pois.csv.
     * @return The number of added POIs.
     */
    int addPOIs(String inputPOIsFile);

    /**
     * Add Users to the system.
     *
     * Basic: Add one User.
     * Intermediate: Add several Users.
     *
     * @param inputUsersFile Path to file that contains the Users, according
     *                       to file input/users.csv.
     * @return The number of added users.
     */
    int addUsers(String inputUsersFile);

    /**
     * Add Paths to the system.
     *
     * @param inputPathsFile Path to file that contains the Paths, according
     *                       to file input/paths.csv.
     * @return The number of added Paths.
     */
    int addPaths(String inputPathsFile);

    /**
     * Get the list of bicycles parked at a given park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param outputFileName Path to file where output should be written,
     *                       according to file output/bicycles.csv. Sort in
     *                       ascending order by bike description.
     * @return The number of bicycles at a given park.
     */
    int getNumberOfBicyclesAtPark(double parkLatitudeInDegrees,
                                  double parkLongitudeInDegrees,
                                  String outputFileName);

    /**
     * Get the number of free parking places at a given park for the loaned
     * bicycle.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username The username that has an unlocked bicycle.
     *
     * @return The number of free slots at a given park for the user's
     * bicycle type.
     */
    int getFreeSlotsAtPArk(double parkLatitudeInDegrees,
                           double parkLongitudeInDegrees, String username);

    /**
     * Get a list of the nearest parks to the user.
     *
     * @param userLatitudeInDegrees User latitude in Decimal Degrees.
     * @param userLongitudeInDegrees User longitude in Decimal Degrees.
     * @param outputFileName Path to file where output should be written,
     *                       according to file output/pois.csv. Sort by
     *                       distance in ascending order.
     */
    void getNearestParks(double userLatitudeInDegrees,
                         double userLongitudeInDegrees, String outputFileName);

    /**
     * Get the distance from a location to another.
     *
     * @param destinyLatitudeInDegrees Origin latitude in Decimal Degrees.
     * @param originLongitudeInDegrees Origin longitude in Decimal Degrees.
     * @param destinyLatitudeInDegrees Destiny latitude in Decimal Degrees.
     * @param destinyLongitudeInDegrees Destiny longitude in Decimal Degrees.
     * @return Returns the distance in meters from one location to another.
     */
    int distanceTo(double originLatitudeInDegrees,
                   double originLongitudeInDegrees,
                   double destinyLatitudeInDegrees,
                   double destinyLongitudeInDegrees);

    /**
     * Unlocks a specific bicycle.
     *
     * @param username User that requested the unlock.
     * @param bicycleDescription Bicycle description to unlock.
     * @return The time in miliseconds at which the bicycle was unlocked.
     */
    long unlockBicycle(String bicycleDescription, String username);

    /**
     * Lock a specific bicycle at a park.
     *
     * Basic: Lock a specific bicycle at a park.
     * Intermediate: Charge the user if 1h is exceeded.
     * Advanced: Add points to user.
     *
     * @param bicycleDescription Bicycle to lock.
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @return The time in miliseconds at which the bicycle was locked.
     */
    long lockBicycle(String bicycleDescription, double parkLatitudeInDegrees,
                     double parkLongitudeInDegrees);

    /**
     * Return the current debt for the user.
     *
     * @param username The user to get the debt from.
     * @param outputFileName The path for the file to output the debt,
     *                       according to file output/balance.csv.
     *                       Sort the information by unlock time in ascending
     *                       order (oldest to newest).
     * @return The User's current debt in euros, rounded to two decimal places
     */
    double getUserCurrentDebt(String username, String outputFileName);

    /**
     * Return the current points for the user.
     * @param username The user to get the points report from.
     * @param outputFileName The path for the file to output the points,
     *                       according to file output/points.csv.
     *                       Sort the information by unlock time in ascenind
     *                       order (oldest to newest).
     * @return The User's current points.
     */
    double getUserCurrentPoints(String username, String outputFileName);

    /**
     * Unlocks an available bicycle at one park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username User that requested the unlock.
     * @param outputFileName Write the unlocked bicycle information to a file,
     *                       according to file output/bicycles.csv.
     * @return The time in milisendons at which the bicycle was unlocked.
     */
    long unlockAnyBicycleAtPark(double parkLatitudeInDegrees,
                                double parkLongitudeInDegrees,
                                String username,
                                String outputFileName);

    /**
     * Unlocks an electrical bicycle at one park. It should unlock the one
     * with higher battery capacity.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param username User that requested the unlock.
     * @param outputFileName Write the unlocked bicycle information to a file,
     *                       according to file output/bicycles.csv.
     * @return The time in milisendons at which the bicycle was unlocked.
     */
    long unlockAnyElectricBicycleAtPark(double parkLatitudeInDegrees,
                                        double parkLongitudeInDegrees,
                                        String username,
                                        String outputFileName);

    /**
     * Calculate the amount of electrical energy required to travel from one
     * park to another.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in
     *                                     Decimal degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     *                                      Decimal degrees.
     * @param username Username.
     * @return The electrical energy required in kWh, rounded to two decimal
     * places.
     */
    double calculateElectricalEnergyToTravelFromOneLocationToAnother(
        double originLatitudeInDegrees,
        double originLongitudeInDegrees,
        double destinationLatitudeInDegrees,
        double destinationLongitudeInDegrees,
        String username);

    /**
     * Suggest an electrical bicycle with enough energy + 10% to go from one
     * Park to another.
     *
     * @param originParkLatitudeInDegrees Origin Park latitude in Decimal
     *                                    degrees.
     * @param originParkLongitudeInDegrees Origina Park Longitude in Decimal
     *                                     degrees.
     * @param destinationParkLatitudeInDegrees Destination Park latitude in
     *                                         Decimal degrees.
     * @param destinationParkLongitudeInDegrees Destination Park Longitude in
     *                                          Decimal degrees.
     * @param username Username.
     * @param outputFileName Write the bicycles information to a file,
     *                       according to file output/bicycles.csv.
     * @return The number of suggested bicycles.
     */
    int suggestElectricalBicyclesToGoFromOneParkToAnother(
        double originParkLatitudeInDegrees,
        double originParkLongitudeInDegrees,
        double destinationParkLatitudeInDegrees,
        double destinationParkLongitudeInDegrees,
        String username,
        String outputFileName);

    /**
     * Get for how long has a bicycle been unlocked.
     *
     * @param bicycleDescription Bicycle description.
     * @return The time in seconds since the bicycle was unlocked.
     */
    long forHowLongWasTheBicycleUnlocked(String bicycleDescription);


    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available.
     * Intermediate: Consider that connections between locations are not
     * bidirectional.
     * Advanced: More than one Route between two parks are available with
     * different number of points inbetween and different evelations difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in
     *                                     Decimal degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     *                                      Decimal degrees.
     * @param outputFileName Write to the file the Route between two parks
     *                   according to file output/paths.csv. More than one
     *                   path may exist. If so, sort routes by the ascending
     *                   number of points between the parks and by ascending
     *                   order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    long shortestRouteBetweenTwoParks(
        double originLatitudeInDegrees,
        double originLongitudeInDegrees,
        double destinationLatitudeInDegrees,
        double destinationLongitudeInDegrees,
        String outputFileName);

    /**
     * Calculate the most energetically efficient route from one park to
     * another using any bicycle.
     *
     * Basic: Does not consider wind.
     * Intermediate: Considers wind.
     * Advanced: Considers the different mechanical and aerodynamic
     * coefficients.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in
     *                                     Decimal degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     *                                      Decimal degrees.
     *
     * @param typeOfBicyle The type of bicycle required e.g. "electric", "mtb"
     *                    or "road".
     * @param username The username.
     * @param outputFileName Write to the file the Route between two parks
     *                   according to file output/paths.csv. More than one
     *                   path may exist. If so, sort routes by the ascending
     *                   number of points between the parks and by ascending
     *                   order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    long mostEnergyEfficientRouteBetweenTwoParks(
        double originLatitudeInDegrees,
        double originLongitudeInDegrees,
        double destinationLatitudeInDegrees,
        double destinationLongitudeInDegrees,
        String typeOfBicyle,
        String username,
        String outputFileName);

    /**
     * Calculate the shortest Route from one park to another.
     *
     * Basic: Only one shortest Route between two Parks is available.
     * Intermediate: Consider that connections between locations are not
     * bidirectional.
     * Advanced: More than one Route between two parks are available with
     * different number of points inbetween and different evelations difference.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in
     *                                     Decimal degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     *                                      Decimal degrees.
     * @param inputPOIs Path to file that contains the POIs that the route
     *                  must go through, according to file input/pois.csv.
     * @param outputFileName Write to the file the Route between two parks
     *                   according to file output/paths.csv. More than one
     *                   path may exist. If so, sort routes by the ascending
     *                   number of points between the parks and by ascending
     *                   order of elevation difference.
     * @return The distance in meters for the shortest path.
     */
    long shortestRouteBetweenTwoParksForGivenPOIs(
        double originLatitudeInDegrees,
        double originLongitudeInDegrees,
        double destinationLatitudeInDegrees,
        double destinationLongitudeInDegrees,
        String inputPOIs,
        String outputFileName);

    /**
     * Get a report for the bicycle charging status at a given park.
     *
     * @param parkLatitudeInDegrees Park latitude in Decimal degrees.
     * @param parkLongitudeInDegrees Park Longitude in Decimal degrees.
     * @param outputFileName Path to file where bicycle information should be
     *                       written, according to file output/bicycles.csv.
     *                       Sort items by descending order of time to finish
     *                       charge in seconds and secondly by ascending bicycle
     *                       description order.
     * @return The number of bicycles charging at the moment that are not
     * 100% fully charged.
     */
    long getParkChargingReportForPark(
        double parkLatitudeInDegrees,
        double parkLongitudeInDegrees,
        String outputFileName);

    /**
     * Calculate the most energetically efficient route from one park to
     * another with sorting options.
     *
     * @param originLatitudeInDegrees Origin latitude in Decimal degrees.
     * @param originLongitudeInDegrees Origin Longitude in Decimal degrees.
     * @param destinationLatitudeInDegrees Destination Park latitude in
     *                                     Decimal degrees.
     * @param destinationLongitudeInDegrees Destination Park Longitude in
     *                                      Decimal degrees.
     *
     * @param typeOfBicycle The type of bicycle required e.g. "electric", "mtb"
     *                    or "road".
     * @param username The user that asked for the routes.
     * @param maxNumberOfSuggestions The maximum number of suggestions to
     *                               provide.
     * @param ascendingOrder If routes should be ordered by ascending or
     *                       descending order
     * @param sortingCriteria The criteria to user for ordering "energy",
     *                        "shortest_distance", "number_of_points"
     * @param inputPOIs Path to file that contains the POIs that the route
     *                  must go through, according to file input/pois.csv.
     *                  By default, the file is empty.
     * @param outputFileName Write to the file the Route between two parks
     *                   according to file output/paths.csv. More than one
     *                   path may exist.
     * @return The number of suggestions
     */
    int suggestRoutesBetweenTwoLocations(
        double originLatitudeInDegrees,
        double originLongitudeInDegrees,
        double destinationLatitudeInDegrees,
        double destinationLongitudeInDegrees,
        String typeOfBicycle,
        String username,
        int maxNumberOfSuggestions,
        boolean ascendingOrder,
        String sortingCriteria,
        String inputPOIs,
        String outputFileName);


    /**
     * Get the invoice for the current month.
     * This should include all bicycle loans that charged the user, the
     * number of points the user had before the actual month, the number of
     * points earned during the month, the number of points converted to euros.
     *
     * @param month The month of the invoice e.g. 1 for January.
     * @param username The user for which the invoice should be created.
     * @param outputPath Path to file where the invoice should be written,
     *                   according to file output/invoice.csv.
     * @return User debt in euros rounded to two decimal places.
     */
    double getInvoiceForMonth(int month, String username,
                              String outputPath);
}

