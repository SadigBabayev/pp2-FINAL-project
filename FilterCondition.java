class FilterCondition {
    String attribute;
    String operator;
    String value;

    public FilterCondition(String attribute, String operator, String value) {
        this.attribute = attribute.toLowerCase();
        this.operator = operator.toLowerCase();
        this.value = value.toLowerCase();
    }

    public boolean matches(Car car) {
        switch (attribute) {
            case "make":
                return compareString(car.getMake());
            case "model":
                return compareString(car.getModel());
            case "color":
                return compareString(car.getColor());
            case "transmission":
                return compareString(car.getTransmission());
            case "fueltype":
                return compareString(car.getFuelType());
            case "sellername":
                return compareString(car.getSeller().getName());
            case "sellerphone":
                return compareString(car.getSeller().getPhone());
            case "selleremail":
                return compareString(car.getSeller().getEmail());
            case "id":
                return compareNumber(car.getId());
            case "year":
                return compareNumber(car.getYear());
            case "price":
                return compareDouble(car.getPrice());
            case "mileage":
                return compareNumber(car.getMileage());
            default:
                return false;
        }
    }

    private boolean compareString(String carValue) {
        switch (operator) {
            case "equals":
                return carValue.equalsIgnoreCase(value);
            case "contains":
                return carValue.toLowerCase().contains(value);
            default:
                return false;
        }
    }

    private boolean compareNumber(int carValue) {
        try {
            int val = Integer.parseInt(value);
            switch (operator) {
                case "equals": return carValue == val;
                case "greater": return carValue > val;
                case "less": return carValue < val;
                default: return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean compareDouble(double carValue) {
        try {
            double val = Double.parseDouble(value);
            switch (operator) {
                case "equals": return carValue == val;
                case "greater": return carValue > val;
                case "less": return carValue < val;
                default: return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
