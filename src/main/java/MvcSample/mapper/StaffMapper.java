package MvcSample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import MvcSample.domain.Staff;
import MvcSample.domain.form.StaffForm;


@Mapper
public interface StaffMapper {

    @Select("select * from m_staff where loginid = #{loginid}")
    Staff findByLoginid(String loginid);

    @Select("select * from m_staff order by loginid")
    List<Staff> selectAll();


    void insertStaff(StaffForm staff);
}
