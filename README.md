# Programmers.Level3_Java_DiskController

## 프로그래머스 힙(Heap) > 디스크 컨트롤러

### 1. 문제설명
문제: https://programmers.co.kr/learn/courses/30/lessons/42627

input으로 Job들의 도착시간, 수행시간이 int[][]형으로 온다. 전체 Job들의 반환시간의 평균이 최소가 되도록 하는 스케줄러를 작성할 때, 최소 평균 반환시간을 return 하는 문제이다.

이때 최소 반환시간은 대기하고 있는 Job들중에 가장 짧은 수행시간을 가진 Job을 우선순위로 처리할때 평균적으로 최소 반환시간을 기대할 수 있다.

### 2. 풀이

도착시간과 수행시간을 가진 Job 클래스를 만들고 낮은 수행시간을 가진 Job이 수행될때 먼저 수행될 수 있도록 compareTo를 구현해주었다. input으로 job에 대한 정보가 도착시간에 오름차순으로 올 수 있도록 전처리 
```java
Arrays.sort(jobs, new Comparator<int[]>() {
			public int compare(final int[] entry1,final int[] entry2) {
				final int time1 = entry1[0];
				final int time2 = entry2[0];
				return Integer.compare(time1, time2);
			}
		});
```
를 해주어 Queue에 담는다.

처리된 작업의 개수가 총 입력 작업 개수보다 작을때 계속 loop를 돌게 하였다. 반복문 안은 세가지 부분으로 이루어 작성하였다. 

  **1) 현재 작업을 수행중일 때**

  작업이 수행되었을 때의 시간으로 현재 시간 조정, 처리된 작업수 +1, 총 반환시간에 이번 작업의 반환시간을 더함
  
  **2) JobList확인**

  1번의 수행으로 변경된 시간보다 일찍이 도착한 작업이 존재한다면, jobList에서 우선순위 큐로 가져옴
    > 우선순위 큐는 수행시간이 짧은 Job의 우선순위를 높게 잡는다
  
  **3) 수행중인 작업이 없을 때**
  
  curJob을 우선순위 큐에서 첫번째 인자로 지정하고 loop를 계속한다
    > 이 작업은 다음 반복 1)에서 수행된다.
  
### 3. 배운점
Job 클래스에 implements Comparable을 추가하여 우선순위 큐를 구성하였는데 같은 Job이지만 빨리 도착한 작업에 우선순위를 두고 정렬이 필요한 경우도 존재하였다. 이때는 ```Arrays.sort()```를 사용하여서 문제를 해결하였는데 객체를 비교하는 두가지 경우가 있을 때는 클래스에 상속을 두어서 처리하는 것 보다 
```java
Arrays.sort(list, new Comparator<>() {})
Collections.sort(list, new Comparator<>() {})
```
로 각 자료를 담을 container에 따라 compareTo를 작성하는 방법이 더 정돈된 방법같다.
