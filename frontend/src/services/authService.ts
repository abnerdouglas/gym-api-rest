
export const authenticate = async (email: string, password: string) => {
  const response = await fetch('http://localhost:8080/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email, password }),
  });

  if (!response.ok) {
    const error = await response.json();
    throw new Error(error.message || 'Erro no login');
  }

  return await response.json();
};

export const register = async (name: string, cpf: string, dateOfBirth: string, email: string, password: string, role: string) => {
  const response = await fetch('http://localhost:8080/api/register', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ name, cpf, dateOfBirth, email, password, role }),
  });

  if(!response.ok) {
    const error = await response.json();
    throw new Error(error.message || 'Erro no cadastro');  
  }

  return await response.json();
};

export const getUsers = async () => {
  const response = await fetch('http://localhost:8080/api/users',{
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if(!response.ok) {
    const error = await response.json();
    throw new Error(error.message || 'Erro ao buscar os usuários');  
  }

  return await response.json();
}
