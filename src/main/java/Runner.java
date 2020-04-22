import java.io.File;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws Exception {
       List<Data> result= FileUtils.loadFromFile();
        for(Data tmp:result){
            System.out.println(tmp);
        }
    }
}
