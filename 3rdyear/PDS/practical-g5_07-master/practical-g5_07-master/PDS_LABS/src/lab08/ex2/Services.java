package lab08.ex2;

public class Services {

    private SocialSecurity ss;
    private Insurance ins;
    private Parking park;
    private double avg;

    public Services(SocialSecurity ss, Insurance ins, Parking park){
        this.ss = ss;
        this.ins = ins;
        this.park = park;
    }

    public void RegisterEmployee(Employee e){
        ss.regist(e);
        ins.regist(e);
        makeCartao();
        if(e.getSalary()>avg) park.allow(e);
    }
    
    public void setAvg(double avg){
        this.avg=avg;
    }
    
    private void makeCartao(){
        System.out.println("Card created!");
    }
}
