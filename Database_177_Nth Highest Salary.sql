-- 所有方法：https://leetcode-cn.com/problems/nth-highest-salary/solution/mysql-zi-ding-yi-bian-liang-by-luanz/

-- 方法1：单表查询
-- 由于LIMIT之后只接受正整数或单一变量，所以要事先将N设置为N - 1

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N := N-1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT 
            salary
      FROM 
            employee
      GROUP BY 
            salary
      ORDER BY 
            salary DESC
      # LIMIT后的第一个参数是输出记录的初始位置，第二个参数是输出的条目的数量
      # 注意和LIMIT ... OFFSET ...区分，区别在于参数的顺序不同
      # LIMIT N, 1表示从第N条数据开始输出1条，也就要要求的薪水排名为N的结果
      LIMIT N, 1
  );
END

-- 方法2：子查询
-- 对于employee中的每一行，WHERE中的子查询都会执行一遍，所以效率比较低
-- 注意薪水可能有重复，所以子查询中要用DISTINCT

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      SELECT 
          DISTINCT e.salary
      FROM 
          employee e
      WHERE 
          (SELECT count(DISTINCT salary) FROM employee WHERE salary>e.salary) = N-1
  );
END

-- 方法3：自连接

CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      SELECT 
          e1.salary
      FROM 
          employee e1 JOIN employee e2 ON e1.salary <= e2.salary
      GROUP BY 
          e1.salary
      HAVING 
          count(DISTINCT e2.salary) = N
  );
END