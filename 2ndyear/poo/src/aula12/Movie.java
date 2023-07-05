package aula12;

public class Movie implements Comparable<Movie> {
    private String name;
    private float score;
    private String Rating;
    private String genre;
    private int running_time;
    
    public Movie(String name,float score,String Rating,String genre,int running_time){
        this.name=name;
        this.score=score;
        this.Rating=Rating;
        this.genre=genre;
        this.running_time=running_time;

    }
    
    public String getName(){
        return name;
    }

    public void SetName(String name){
        this.name=name;
    }
    public String genre(){
        return genre;
    }
    public void Setgenre(String genre){
        this.genre=genre;
    }
    public float getScore(){
        return score;
    }
    public void SetScore(float score){
        this.score=score;
    }

    public String getRating(){
        return Rating;
    }

    public void SetRating(String Rating){
        this.Rating=Rating;
    }

    public int running_time(){
        return running_time;
    }
    public void Getrunning_time(int running_time){
        this.running_time=running_time;
    }

    @Override
    public String toString() {
        return "Filme{" + "nome=" + name + ", pontuacao=" + score + ", classificacao=" + Rating + ", genero=" + genre + ", duracao=" + running_time + '}';
    }

    @Override
    public int compareTo(Movie M) {
        return this.getName().toLowerCase().compareTo(M.getName().toLowerCase());
    }
}
