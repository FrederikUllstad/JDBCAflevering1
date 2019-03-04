package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class UserDAOImpls185018 implements IUserDAO {

    private Connection createConnection() throws DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://ec2-52-30-211-3.eu-west-1.compute.amazonaws.com/s185018?"
                    + "user=s185018&password=ZDt6BE2DofFjsZVyjmdij");
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public UserDTO getUser(int userId) throws DALException {
        Connection connection = createConnection();
        UserDTO user = new UserDTO();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aflevering1 WHERE UserID = " + userId);
            resultSet.first();
            user.setUserId(resultSet.getInt("UserID"));
            user.setUserName(resultSet.getString("Username"));
            user.setIni(resultSet.getString("Initials"));
            user.setRoles((List<String>) resultSet.getObject("Roles"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
        return user;
    }



    @Override
    public List<UserDTO> getUserList() throws DALException {
        List<UserDTO> userList = new ArrayList<UserDTO>();
        Connection connection = createConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT UserID FROM Aflevering1");
            while (resultSet.next()){
                int ID = resultSet.getInt("UserID"); UserDTO user = getUser(ID);
                userList.add(user);
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;

    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        Connection connection = createConnection();
        int id = 1;
        String name = "BROR ULLSTAD";
        String ini = "BU";
        String roles = "BROR";
        try {
            connection.prepareStatement("INSERT INTO aflevering1(UserID, Username, Initials, Roles) values(" + id +
                    ", " + name + ", " + ini + ", " + roles + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE aflevering1 SET Username = ? WHERE UserID = ?");
            preparedStatement.setString(1, user.getIni());
            preparedStatement.setInt(2, user.getUserId());

            preparedStatement = connection.prepareStatement("UPDATE aflevering1 SET Initials = ? WHERE UserID = ?");
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setInt(2, user.getUserId());

            preparedStatement = connection.prepareStatement("UPDATE aflevering1 SET Roles = ? WHERE UserID = ?");
            preparedStatement.setString(1, String.valueOf(user.getRoles()));
            preparedStatement.setInt(2, user.getUserId());

            connection.close();
        }catch (SQLException e){
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        Connection connection = createConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM aflevering1 WHERE UserID = " + userId);
            preparedStatement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }
}
