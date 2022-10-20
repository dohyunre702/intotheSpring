package com.likelion;

public class Prac {
    public static void main(String[] args) {
        //환경변수 : 운영체제에서 이름(Name)과 값(Value)으로 관리되는 문자열 정보
        //운영체제 설치 시 기본적인 내용이 설정되지만, 사용자가 직접 설정하거나 응용프로그램이 설치될 때 자동적으로 변경되기도 한다.
        //OS 환경변수의 값을 System.getenv() 메서드를 통해 불러온다.

        // String value = System.getenv(String name); 사용법
        System.out.println("전체 OS 환경변수 값:" + System.getenv());  //기본형
        System.out.println("OS 환경변수 NYM_SYMLINK값:" + System.getenv("NYM_SYMLiNK")); //인자로 환경변수 이름을 줌
        System.out.println("OS 환경변수 JAVA_HOME값:" + System.getenv("JAVA_HOME")); //인자로 환경변수 이름을 줌

    }
}
