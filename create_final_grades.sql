
insert into subject values (22, 'Bachelorarbeit', 0)

insert into subject values (23, 'Kolloquium', 0)

select * from subject
select * from written_exam where student_id = 3

insert into written_exam values (56, 22, '2.3', 3)
insert into written_exam values (57, 23, '2.0', 3)

create view final_grades as
select student_id, sum(grade * faktor) as final_grade
from (
select e.student_id, sum(convert(convert(e.grade, char(100)), decimal(10,6))) / count(e.id) as grade, 0.75 as faktor
from written_exam e
inner join subject s on e.subject_id = s.id
where s.ects > 0
group by e.student_id
union all
select e.student_id, sum(convert(convert(e.grade, char(100)), decimal(10,6))) / count(e.id) as grade
  , case when s.name = 'Bachelorarbeit' then 0.2 when s.name = 'Kolloquium' then 0.05 else 0.0 end as faktor
from written_exam e
inner join subject s on e.subject_id = s.id
where s.ects = 0
group by e.student_id, s.name
) t
group by student_id



ALTER TABLE written_exam modify column grade enum('1.0','1.3','1.7','2.0','2.3','2.7','3.0','3.3','3.7','4.0','5.0');

insert into written_exam values (99, 23, '5.0', 3)


select *
from final_grades
