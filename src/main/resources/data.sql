INSERT INTO study(title, people, goal)
VALUES ('Problem Solving Study', 7, 'Always do your best!');

INSERT INTO curriculum(week_num, title, absent, announcement, study_id)
VALUES (1, '백준 실버문제 다풀기', false, '1주차는 필수입니다', 1),
       (2, '백준 골드문제 다풀기', true, '2주차는 필수입니다', 1),
       (3, '백준 플레티넘문제 다풀기', false, '3주차는 필수입니다', 1);

INSERT INTO assignment(week_num, assignment, absent, curriculum_id)
VALUES (1, 'https://github.com/sohyundoh/Spring_Lab', true, 1),
       (1, '', false, 1),
       (1, '', false, 1);

INSERT INTO comment(body, assignment_id)
VALUES ('5-6번 째 사용하지 않는 필드 선언되어 있습니다!재할당하지 않고 바로 memberIter을 사용해도 되지 않을까요? 재할당하고 for문 밖에서 사용하지 않는 것 같아서요!', 1);