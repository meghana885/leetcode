WITH total_users AS (
    SELECT COUNT(DISTINCT user_id) AS total_count
    FROM Users
)
SELECT 
    r.contest_id,
    ROUND(
        (COUNT(DISTINCT r.user_id) * 100.0) / t.total_count,
        2
    ) AS percentage
FROM Register r
CROSS JOIN total_users t
GROUP BY r.contest_id, t.total_count
ORDER BY percentage DESC, r.contest_id;
