package dal;

import dto.UserDTO;

import java.sql.*;
import java.util.List;

//TODO Rename class
public class UserDAOImpls185018 implements IUserDAO {
    //TODO Make a connection to the database
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
        //TODO Implement this
        Connection connection = createConnection();
        UserDTO user = new UserDTO();
        Connection c = createConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM aflevering_1 WHERE UserID = " + userId);
            resultSet.first();
            user.setUserId(resultSet.getInt("UserID"));
            user.setUserName(resultSet.getString("Username"));
            user.setIni(resultSet.getString("Initials"));
            user.setRoles((List<String>)resultSet.getObject("Roles"));



        //TODO: Make a user from the resultset
        UserDTO user = new UserDTO();

        try {
            c.close();
        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
        return user;
    }



    @Override
    public List<UserDTO> getUserList() throws DALException {
        //TODO Implement this
        return null;
    }

    @Override
    public void createUser(UserDTO user) throws DALException {
        //TODO Implement this
    }

    @Override
    public void updateUser(UserDTO user) throws DALException {
        //TODO Implement this
    }

    @Override
    public void deleteUser(int userId) throws DALException {
        //TODO Implement this
    }
}
