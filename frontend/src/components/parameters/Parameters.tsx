import React, { useState } from 'react';
import { Box, Button, HStack, Select } from '@chakra-ui/react';
import Muscle from './Muscle';
import TypeExercise from './TypeExercise';
import Difficulty from './Difficulty';
import { exercisesAPI } from '@/services/exercisesService';

const Parameters = () => {
  const [selectedParameter, setSelectedParameter] = useState('');
  const [parameter1, setParameter1] = useState('');
  const [parameter2, setParameter2] = useState('');

  const handleSelectChange = (e:any) => {
    setSelectedParameter(e.target.value);
  };

  const handleSubmit = async(e: React.FormEvent) => {
    e.preventDefault();
    try {
      const data = await exercisesAPI(parameter1, parameter2);
      console.log(data);
    } catch (error) {
      console.error(error);
    }
  }

  return (
    <Box p={4}>
      <HStack spacing={5} direction="row">
        <Select
          placeholder="Selecione um parâmetro"
          onChange={handleSelectChange}
          borderColor="blue.500"
        >
          <option value="muscle"> Músculo </option>
          <option value="type"> Tipo de Exercício </option>
          <option value="difficulty"> Dificuldade </option>
        </Select>
        {selectedParameter === 'muscle' && (
          <Muscle setParameter1={setParameter1}/>
        )}
        {selectedParameter === 'type' && (
          <TypeExercise/>
        )}
        {selectedParameter === 'difficulty' && (
          <Difficulty/>
        )}
         <Button
                  bg="blue.400"
                  color="white"
                  _hover={{ bg: 'blue.500' }}
                  type="submit"
                  width={'30vh'}
                  onClick={handleSubmit}
                >
                  Buscar
                </Button>
      </HStack>
    </Box>
  );
};

export default Parameters;
