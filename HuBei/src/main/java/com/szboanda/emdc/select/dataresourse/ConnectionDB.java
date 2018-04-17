package com.szboanda.emdc.select.dataresourse;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.szboanda.emdc.select.common.Constants;
import com.szboanda.emdc.select.dataresourse.provider.AbstractConnectionProvider;

/**
 * 数据库连接类 
 * 
 * @author zhangsheng
 * 
 */
public class ConnectionDB {

    /**
     * 创建数据库连接对象
     */
    private Connection connection;

    /**
     * 创建PreparedStatement对象
     */
    private PreparedStatement preparedStatement;

    /**
     * 创建CallableStatement对象
     */
    private CallableStatement callableStatement;

    /**
     * 创建结果集对象
     */
    private ResultSet resultSet;

    /**
     * 数据源名称
     */
    private String dataSourceName;

    /**
     * 无参构造器
     * @throws Exception 
     */
    public ConnectionDB(String dataSourceName) {
        if (StringUtils.isEmpty(dataSourceName)) {
            this.dataSourceName = Constants.DEFAULT_DATA_SOURCE;
        }
        try {
            // 提前获取一次，判断数据库类型
            try {
                this.getConnection(Constants.DEFAULT_DATA_SOURCE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            try {
                throw new Exception("获取数据源出错", e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        closeAll();
    }

    /**
     * 建立数据库连接
     * 
     * @return 数据库连接
     * @throws SQLException
     */
    public Connection getConnection(String dataSourceName) throws Exception {
        if (StringUtils.isEmpty(dataSourceName)) {
            dataSourceName = Constants.DEFAULT_DATA_SOURCE;
        }
        connection = AbstractConnectionProvider.getInstance().getConnection(dataSourceName);
        return connection;
    }

    /**
     * insert update delete SQL语句的执行的统一方法
     * 
     * @param sql
     *            SQL语句
     * @param params
     *            参数数组，若没有参数则为null
     * @return 受影响的行数
     * @throws Exception 
     */
    public int executeUpdate(String sql, Object[] params) throws Exception {
        // 受影响的行数
        int affectedLine = 0;

        try {
            // 获得连接
            connection = this.getConnection(this.dataSourceName);
            // 调用SQL
            preparedStatement = connection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            affectedLine = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SQLException("sql=" + sql + "param" + params.toString(), e);
        } finally {
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }

    /**
     * 多条语句放在一个事务中执行
     * @param sqls
     * @param params
     * @return
     * @throws SQLException 
     * @throws DataSourceException
     */
    public int executeUpdate(List<String> sqls, List<Object[]> params) throws Exception {
        // 受影响的行数
        int affectedLine = 0;
        if (null == sqls || sqls.isEmpty()) {
            return affectedLine;
        }
        try {
            // 获得连接
            connection = this.getConnection(this.dataSourceName);
            connection.setAutoCommit(false);
            for (int i = 0; i < params.size(); i++) {
                if (null == sqls.get(i)) {
                    continue;
                }
                // 调用SQL
                preparedStatement = connection.prepareStatement(sqls.get(i));

                // 参数赋值
                if (params.get(i) != null) {
                    for (int j = 0; j < params.get(i).length; j++) {
                        preparedStatement.setObject(j + 1, params.get(i)[j]);
                    }
                }
                // 执行
                affectedLine = preparedStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
            throw new SQLException("sql=" + sqls.toString(), e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
            // 释放资源
            closeAll();
        }
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     * 
     * @param sql
     *            SQL语句
     * @param params
     *            参数数组，若没有参数则为null
     * @return 结果集
     * @throws SQLException 
     */
    private ResultSet executeQueryRS(String sql, Object[] params) throws Exception {
        try {
            // 获得连接
            connection = this.getConnection(this.dataSourceName);

            // 调用SQL
            preparedStatement = connection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }

            // 执行
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new SQLException("执行sql异常\n\tsql=" + sql, e);
        }

        return resultSet;
    }

    /**
     * SQL 查询将查询结果：一行一列
     * 
     * @param sql SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     * @throws SQLException 
     */
    public Object executeQuerySingle(String sql, Object[] params) throws Exception {
        Object object = null;
        try {
            // 获得连接
            connection = this.getConnection(this.dataSourceName);

            // 调用SQL
            preparedStatement = connection.prepareStatement(sql);

            // 参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            // 执行
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                object = resultSet.getObject(1);
            }

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集，并将结果放在List中
     * 
     * @param sql
     *            SQL语句
     * @return List 结果集
     * @throws SQLException 
     */
    public List<Map<String, Object>> excuteQuery(String sql, Object[] params) throws Exception {
        // 执行SQL获得结果集
        ResultSet rs = executeQueryRS(sql, params);
        // 创建ResultSetMetaData对象
        ResultSetMetaData rsmd = null;
        // 结果集列数
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();
            // 获得结果集列数
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e) {
            throw new SQLException("\nsql=" + sql, e);
        }

        // 创建List
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        try {
            // 将ResultSet的结果保存到List中
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            throw new SQLException("\nsql=" + sql, e);
        } finally {
            // 关闭所有资源
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法
     * 
     * @param sql
     *            存储过程语句
     * @param params
     *            参数数组
     * @param outParamPos
     *            输出参数位置
     * @param SqlType
     *            输出参数类型
     * @return 输出参数的值
     * @throws SQLException 
     */
    public Object excuteQuery(String sql, Object[] params, int outParamPos, int SqlType) throws Exception {
        Object object = null;
        try {
            connection = this.getConnection(this.dataSourceName);
            // 调用存储过程
            callableStatement = connection.prepareCall(sql);

            // 给参数赋值
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            }
            // 注册输出参数
            callableStatement.registerOutParameter(outParamPos, SqlType);
            // 执行
            callableStatement.execute();

            // 得到输出参数
            object = callableStatement.getObject(outParamPos);

        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            // 释放资源
            closeAll();
        }

        return object;
    }

    /**
     * 关闭所有资源
     */
    private void closeAll() {
        // 关闭结果集对象
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭PreparedStatement对象
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭CallableStatement 对象
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // 关闭Connection 对象
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}