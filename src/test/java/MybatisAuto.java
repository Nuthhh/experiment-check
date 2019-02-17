/**
 * Created by 韩建国 on 2019/2/17
 */

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/17 23:32
 * @Description:
 **/
public class MybatisAuto {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> list = new ArrayList<>();
        File config = new File(System.getProperty("user.dir") + "/src/main/resources/mybatis/generatorConfig.xml");
//System.out.println(config.exists());
        ConfigurationParser cp = new ConfigurationParser(list);
        Configuration configuration = cp.parseConfiguration(config);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, callback, list);
        myBatisGenerator.generate(null);
        System.out.println("ok");
    }
}
