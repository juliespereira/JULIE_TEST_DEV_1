public class CompanyDto {
    String name;
    String segment;
    double standardDeviation;

    public CompanyDto(String name, String segment, double standardDeviation) {
        this.name = name;
        this.segment = segment;
        this.standardDeviation = standardDeviation;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSegment(){
        return segment;
    }

    public void setSegment(String segment){
        this.segment = segment;
    }

    public String getStandardDeviation(){
        return standardDeviation;
    }

    public void setStandardDeviation(String standardDeviation){
        this.standardDeviation = standardDeviation;
    }

}


