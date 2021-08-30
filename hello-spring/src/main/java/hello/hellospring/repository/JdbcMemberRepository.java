package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements  MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try{
            if(rs != null){
                rs.close();
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(pstmt != null) {
                pstmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn != null){
                close(conn);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException{
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
        // Spring framework를 통해 dataSource를 사용할 경우 DataSourceUtils를 통해 connection을 획득해야한다.
        // 이전에 transaction에 걸릴 수 있는데 이 때 같은 database connection을 유지해야 한다.
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;        // 결과를 받음

        try{
            conn = getConnection();     //connection을 가져온다.
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Statement.RETURN_GENERATED_KEYS는 삽입되는 id를 얻게 해준다.

            pstmt.setString(1, member.getName());
            // 물음표에 member.getName()을 넣어준다.

            pstmt.executeUpdate();  // db에 실제 쿼리가 날라감
            rs = pstmt.getGeneratedKeys();  // db가 생성한 key를 반환해준다.

            if (rs.next()){
                member.setId(rs.getLong(1));    // 값이 있으면 값을 꺼낸다.
            }else{
                throw new SQLException("id 조회 실패");     // 실패한 경우 exception
            }
            return member;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pstmt,rs);       // 자원 release
        }
    }


    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);

            rs = pstmt.executeQuery();

            if(rs.next()){      // 값이 있으면
                Member member = new Member();
                member.setId(rs.getLong("id"));     //  id를 가져옴
                member.setName(rs.getString("name"));   // name을 가져옴
                return Optional.of(member);         // 반환
            }else{
                return Optional.empty();
            }
        }catch(Exception e){
            throw new IllegalStateException(e);
        } finally{
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }

            return Optional.empty();
        } catch (Exception e){
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();

            List<Member> members = new ArrayList<>();
            while(rs.next()){       // 계속 다음이 있는지 확인하며 members list에 담는다.
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;
        }catch(Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, rs);
        }
    }
}
