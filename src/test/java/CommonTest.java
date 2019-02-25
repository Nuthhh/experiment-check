import com.ysu.common.utils.FileUtil;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

import java.io.IOException;
import java.math.BigInteger;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/21 15:33
 * @Description:
 **/
public class CommonTest {

    public static void main(String[] args) throws IOException, OpenXML4JException, XmlException {

        String context = FileUtil.readWordByUrl("http://39.105.156.250/file/document/ww.docx");
        //String context = FileUtil.readWord("C:\\Users\\hjg\\Desktop\\毕设20190222\\ww.docx");
        System.out.println(context);

    }
}
