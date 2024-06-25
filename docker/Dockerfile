# Dockerfile for MySQL container in dayeon project

# MySQL을 사용할 수 있는 베이스 이미지를 선택합니다.
FROM mysql:8.0

# 환경 변수 설정 (옵션)
ENV MYSQL_DATABASE=dayeon
ENV MYSQL_USER=dayeon
ENV MYSQL_PASSWORD=dayeon
ENV MYSQL_ROOT_PASSWORD=tjddnr12

# MySQL 포트 (기본값: 3306)를 외부에 노출합니다.
EXPOSE 3306

# MySQL 데이터 디렉토리를 호스트에 마운트 (선택사항)
VOLUME /var/lib/mysql