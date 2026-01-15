package postech.unifiedeats.unified_eats_api.docs;

public final class ApiExamples {

    private ApiExamples() {
    }

    /* =========================
       REQUEST EXAMPLES
       ========================= */

    public static final String USER_CREATE_REQUEST = """
{
  "name": "João Silva",
  "email": "joao@email.com",
  "login": "joaosilva",
  "password": "123456",
  "type": "CUSTOMER",
  "addressDTO": {
    "zipCode": "03300-000",
    "street": "Rua das Flores",
    "number": "123",
    "complement": "Apto 12",
    "district": "Vila Carrão",
    "city": "São Paulo",
    "state": "SP"
  }
}
""";


    public static final String USER_UPDATE_REQUEST = """
{
  "name": "João Silva Atualizado",
  "email": "joao.novo@email.com",
  "login": "joaosilva",
  "type": "CUSTOMER",
  "addressDTO": {
    "zipCode": "03310-000",
    "street": "Rua B",
    "number": "456",
    "complement": "Casa",
    "district": "Tatuapé",
    "city": "São Paulo",
    "state": "SP"
  }
}
""";


    public static final String CHANGE_PASSWORD_REQUEST = """
            {
              "oldPassword": "123456",
              "newPassword": "novaSenha123"
            }
            """;

    public static final String LOGIN_REQUEST = """
            {
              "login": "joaosilva",
              "password": "123456"
            }
            """;

    /* =========================
       SUCCESS RESPONSE EXAMPLES
       ========================= */

    public static final String LOGIN_SUCCESS_RESPONSE = """
            {
              "name": "João Silva",
              "userType": "CUSTOMER"
            }
            """;

    /* =========================
       PROBLEM DETAIL – ERRORS
       ========================= */

    // 400 – Validation error (DTO / parâmetros)
    public static final String PROBLEM_VALIDATION_ERROR = """
            {
              "type": "urn:problem:validation-error",
              "title": "Erro de validação",
              "status": 400,
              "detail": "Campos inválidos na requisição",
              "instance": "/v1/users",
              "errors": {
                "email": ["Email inválido"],
                "password": ["Nova senha deve ter no mínimo 6 caracteres"]
              }
            }
            """;

    // 400 – Invalid parameter (query param, por exemplo)
    public static final String PROBLEM_INVALID_PARAMETER = """
{
  "type": "urn:problem:validation-error",
  "title": "Erro de validação",
  "status": 400,
  "detail": "Parâmetro inválido",
  "instance": "/v1/users/search",
  "errors": {
    "name": ["Parâmetro 'name' é obrigatório"]
  }
}
""";


    // 404 – User not found
    public static final String PROBLEM_USER_NOT_FOUND = """
            {
              "type": "urn:problem:user-not-found",
              "title": "Recurso não encontrado",
              "status": 404,
              "detail": "Usuário não encontrado",
              "instance": "/v1/users/999"
            }
            """;

    // 409 – Conflict (email/login duplicado)
    public static final String PROBLEM_CONFLICT = """
            {
              "type": "urn:problem:conflict",
              "title": "Conflito",
              "status": 409,
              "detail": "Email ou login já cadastrado",
              "instance": "/v1/users"
            }
            """;

    // 401 – Unauthorized (login ou senha inválidos)
    public static final String PROBLEM_UNAUTHORIZED = """
            {
              "type": "urn:problem:unauthorized",
              "title": "Não autorizado",
              "status": 401,
              "detail": "Login ou senha inválidos",
              "instance": "/v1/auth/login"
            }
            """;

    // 401 – Unauthorized (senha atual inválida)
    public static final String PROBLEM_INVALID_PASSWORD = """
            {
              "type": "urn:problem:unauthorized",
              "title": "Não autorizado",
              "status": 401,
              "detail": "Senha atual inválida",
              "instance": "/v1/users/1/password"
            }
            """;
}
