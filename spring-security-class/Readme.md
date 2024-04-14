# Spring Security

생성자: 범진 최
생성 일시: 2024년 4월 13일 오후 1:30
태그: 시큐리티 강의

## 왜 Spring 사용자들은 Spring Security 를 선택 할까?

1. 애플리케이션 보안은 개발자가 개발한 코드/프레임워크로 구현하는 것이 번거럽고 어렵다
2. 보안에 능숙한 Spring Security팀이 모든 보안 시나리오를 고려하여 구축했다.
3. 최소한의 구성으로 page/api 경로 등을 보호하며, 권한,메서드 수준 보안 등을 쉽게 적용할 수 있다.
4. CSRF, CORS등과 같은 일반적인 보안 취약점을 처리 해주고 만약 취약점이 발견되면 프레임워크에 즉시 패치를 진행한다→ 빠른패치
5. 계정 인증,jwt인증, Outh인증,OpenId등 다양한 보안 표준을 지원한다.

## 서블렛과 필터

![Untitled](img/Untitled.png)

### 웹 애플리케이션 내부의 일반적인 시나리오

Java 웹 앱에서 서블릿 컨테이너(웹 서버)는 Java 코드가 이해할 수 있도록 HTTP 메시지를 번역하는 작업을 처리합니다. 가장 많이 사용되는 서블릿 컨테이너 중 하나는 Apache Tomcat입니다. 서블릿 컨테이너는 HTTP 메시지를 서블릿 요청으로 변환하여 매개변수로 서블릿 메서드에 전달합니다. 마찬가지로 서블릿 응답은 서블릿으로부터 서블릿 컨테이너에 출력으로 반환됩니다. 따라서 Java 내부에서 작성하는 모든 웹 앱은 모두 서블릿에 의해 구동됩니다.

### 필터의 역할

Java zveb 애플리케이션 내부의 필터는 각 요청/응답을 가로채고 비즈니스 로직 이전에 몇 가지 사전 작업을 수행하는 데 사용할 수 있습니다. 따라서 동일한 필터를 사용하여 Spring Security는 웹 애플리케이션 내부의 구성을 기반으로 보안을 강화합니다.

## 스프링 시큐리티 내부 흐름

![Untitled](img/Untitled%201.png)

### 스프링 시큐리티 필터

스프링 시큐리티는 각 요청을 가로채는 필터 체인을 통해 인증이 필요한지 여부를 판단합니다. 인증이 필요한 경우, 사용자를 로그인 페이지로 리다이렉트하거나 이전 인증 세션에서 저장된 정보를 재사용합니다.

### Authentication

UsernamePasswordAuthenticationFilter와 같은 필터는 HTTP 요청에서 사용자 이름/비밀번호를 추출하고 Authentication 타입의 객체를 준비합니다. Authentication은 인증된 사용자 세부 정보를 저장하는 핵심 표준이기 때문입니다.

### AuthenticationManager

필터로부터 요청을 받은 인증 매니저는 사용자 세부 정보의 유효성을 검사하기 위해 등록된 인증 제공자들에게 요청을 위임합니다. 애플리케이션에 다수의 인증 제공자가 존재할 수 있기 때문에, 인증 매니저가 이들을 적절히 관리하는 역할을 합니다.

### AuthenticationProvider

필터로부터 요청을 받은 인증 매니저는 사용자 세부 정보의 유효성을 검사하기 위해 등록된 인증 제공자들에게 요청을 위임합니다. 애플리케이션에 다수의 인증 제공자가 존재할 수 있기 때문에, 인증 매니저가 이들을 적절히 관리하는 역할을 합니다.

### UserDetailsManager/UserDetailsService

UserDetailsManager 또는 UserDetailsService는 데이터베이스나 다른 저장소 시스템에서 사용자 세부 정보를 관리(검색, 생성, 수정, 삭제)하는 기능을 제공합니다.

### PasswordEncoder

PasswordEncoder 인터페이스는 비밀번호를 인코딩하고 해싱하는 역할을 합니다. 이는 평문 비밀번호를 보안 위험으로부터 보호하는 데 필수적입니다.

### SecurityContext

요청이 성공적으로 인증을 거친 후, 해당 인증 정보는 보통 SecurityContextHolder에 의해 관리되는 스레드 로컬 보안 컨텍스트에 저장됩니다. 이는 동일 사용자의 후속 요청을 처리할 때 컨텍스트를 유지하는 데 도움을 줍니다.

## 시퀸스 플로우

### Spring Security 기본 동작

![Untitled](img/Untitled%202.png)

![Untitled](img/Untitled%203.png)

1. **보안 페이지에 대한 초기 요청**: 사용자가 처음으로 보안 페이지에 접근하려고 할 때, 요청은 여러 보안 필터를 거칩니다.
2. **필터에 의한 감지 및 리다이렉션**: **`AuthorizationFilter`** 및 **`DefaultLoginPageGeneratingFilter`**와 같은 필터는 사용자가 로그인하지 않았다는 것을 감지하고 로그인 페이지로 리다이렉션합니다.
3. **사용자 자격 증명 제출**: 사용자가 로그인 페이지에서 자격 증명을 입력하면, 로그인 시도를 처리하기 위해 설계된 보안 필터가 요청을 가로챕니다.
4. **추출 및 토큰 형성**: **`UsernamePasswordAuthenticationFilter`**와 같은 필터는 요청에서 사용자 이름과 비밀번호를 추출합니다. 이 정보는 **`Authentication`** 인터페이스의 구현체인 **`UsernamePasswordAuthenticationToken`**을 형성하는 데 사용됩니다.
5. **ProviderManager에 의한 인증 시도**: 이 토큰은 **`AuthenticationManager`**의 구현체인 **`ProviderManager`**에 전달됩니다. **`ProviderManager`**는 주어진 인증 토큰 유형을 지원하는 사용 가능한 **`AuthenticationProvider`** 목록을 식별합니다.
6. **사용자 세부 정보 검색 및 비밀번호 검증**: 일반적으로 이 단계에서는 **`DaoAuthenticationProvider`**가 관여합니다. 이는 **`UserDetailsService`**(예: **`InMemoryUserDetailsManager`**)를 사용하여 메모리에서 사용자 세부 정보를 로드합니다. 검색된 사용자 세부 정보에는 저장된 비밀번호가 포함되어 있으며, 시스템은 제공된 비밀번호와 저장된 비밀번호를 기본 비밀번호 인코더를 사용하여 비교합니다.
7. **인증 성공 또는 실패**: 비밀번호와 사용자 이름이 정확하면 **`DaoAuthenticationProvider`**는 사용자의 인증을 확인하고 성공적인 인증을 나타내는 채워진 **`Authentication`** 객체를 생성합니다. 이 객체에는 사용자의 권한 및 자격 증명과 같은 세부 정보가 포함됩니다.
8. **인증 결과 처리**: **`ProviderManager`**는 결과를 처리합니다. 인증이 실패하면 다른 사용 가능한 제공자로 인증을 시도할 수 있습니다. 성공하면 인증 세부 정보를 필터를 통해 다시 전달합니다.
9. **인증 저장 및 응답**: 인증에 성공하면 **`Authentication`** 객체는 **`SecurityContextHolder`**에 의해 유지되는 **`SecurityContext`**에 저장됩니다. 이 저장소는 일반적으로 스레드 로컬이며, 사용자의 보안 컨텍스트가 세션 전체에 걸쳐 유지되도록 합니다. 인증 실패 시 리다이렉션(인증 실패 시) 또는 성공적인 접근을 나타내는 응답이 사용자에게 반환됩니다.

### USER MANAGEMENT 주요 클래스 &

![Untitled](img/561dcf0f-1d4f-4161-bfcc-1ab9e9de0fdf.png)

### USERDETAILS & AUTHENTICATION 사이의 관계

![Untitled](img/455d6dfd-764c-432c-9165-b5c4a32929e2.png)

"Authentication 객체는 인증 과정이 성공했는지 여부를 판단해야 하는 모든 상황에서의 반환 타입입니다. 예를 들어 AuthenticationProvider와 AuthenticationManager가 이에 해당합니다."

"UserDetails 객체는 저장 시스템에서 사용자 정보를 로딩해야 하는 모든 상황에서의 반환 타입입니다. UserDetailsService와 UserDetailsManager가 작업을 처리할 때 주로 사용됩니다."