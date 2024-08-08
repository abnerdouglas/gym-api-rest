import Cookies from 'js-cookie';

export const exercisesAPI = async (firstParameter: string, secondParameter: string ) => {
    const token = Cookies.get('token');

    const response = await fetch(`http://localhost:8080/api/exercises?${firstParameter}=${secondParameter}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      }
    });
    
    if(!response.ok) {
      const error = await response.json();
      throw new Error(error.message || 'Erro na busca dos exercícios');  
    }
  
    return await response.json();
};