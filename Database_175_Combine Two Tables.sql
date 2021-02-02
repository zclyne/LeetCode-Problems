-- 思路：由于输出的结果中要包含所有的人，而并非每个人都有对应的地址，所以应采用outer join
-- 把Person表作为左表，left join Address表

select FirstName, LastName, City, State
from Person left join Address
on Person.PersonId = Address.PersonId