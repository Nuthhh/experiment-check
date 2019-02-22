import com.ysu.common.utils.FileUtil;
import com.ysu.textsimilarity.HammingDistance;
import com.ysu.textsimilarity.SimHash;


/**
 * @Auther: han jianguo
 * @Date: 2019/2/21 15:43
 * @Description:
 **/
public class TextTest {

    public static void main(String[] args) {
        SimHash h1 = new SimHash(FileUtil.readDocx("D:\\GPU.docx"));
        SimHash h2 = new SimHash(FileUtil.readDocx("D:\\GPU2.docx"));
        System.out.println(h1.getStrSimHash());
        System.out.println(h2.getStrSimHash());
        System.out.println("距离："+HammingDistance.getDistance(h1.getStrSimHash(),h2.getStrSimHash()));


    }
}
