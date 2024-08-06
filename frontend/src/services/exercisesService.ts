export const exercisesAPI = async (firstParameter: string, secondParameter: string ) => {
    const response = await fetch(`http://localhost:8080/api/exercises?${firstParameter}=${secondParameter}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    
    if(!response.ok) {
      const error = await response.json();
      throw new Error(error.message || 'Erro na busca dos exercícios');  
    }
  
    return await response.json();
};