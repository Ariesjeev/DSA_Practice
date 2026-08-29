# Write your MySQL query statement below
SELECT a.user_id,
ROUND(AVG(if (c.action ='confirmed',1,0)),2) as confirmation_rate
FROM Signups a
LEFT JOIN Confirmations c ON a.user_id = c.user_id
GROUP BY a.user_id;