package MvcSample.mapper;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import MvcSample.config.DBTestConfig;
import MvcSample.domain.Staff;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DBTestConfig.class})
@Transactional
public class StaffMapperTest {

    @Autowired
    private StaffMapper mapper;

    @Autowired
    DataSource dataSourceTest;


    @BeforeTransaction
    public void initdb() throws Exception{
        Connection connection = dataSourceTest.getConnection();
        IDatabaseConnection dbconnection = new DatabaseConnection(connection);
//        File file = null;
//        FileOutputStream out;
//        QueryDataSet partialDataSet = new QueryDataSet(dbconnection);
//        partialDataSet.addTable("m_staff");
//        file=new File("src/test/java/MvcSample/mapper/StaffMapperTest.xlsx");
//        out = new FileOutputStream(file);
//        XlsDataSet.write(partialDataSet, out);
//        System.out.println("path: " + file.getPath());
//        out.flush();
//        out.close();

        IDataSet dataset = new XlsDataSet(new File("src/test/java/MvcSample/mapper/StaffMapperTest.xlsx"));
        DatabaseOperation.CLEAN_INSERT.execute(dbconnection, dataset);
    }

    @Test
    public void testSelectAll() {
        assertThat(mapper.selectAll().size(),is(9));
    }

    @Test
    public void testFindByLoginid() {
        Staff staff = mapper.findByLoginid("10001");
        assertThat(staff.getName(),is("TestName"));
    }
}
