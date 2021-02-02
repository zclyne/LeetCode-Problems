-- 解法1：排序后用offset指定第二个
-- 由于不存在时要求返回NULL，所以用临时表来解决这个问题

SELECT
    (SELECT DISTINCT
            Salary
        FROM
            Employee
        ORDER BY Salary DESC
        LIMIT 1 OFFSET 1) AS SecondHighestSalary

-- 解法2：使用IFNULL来解决NULL的问题

SELECT
    IFNULL(
      (SELECT DISTINCT Salary
       FROM Employee
       ORDER BY Salary DESC
        LIMIT 1 OFFSET 1),
    NULL) AS SecondHighestSalary