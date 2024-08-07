import React, { useState } from 'react';
import { Box, Button, HStack, Select, Tooltip, useToast } from '@chakra-ui/react';
import Muscle from './Muscle';
import TypeExercise from './TypeExercise';
import Difficulty from './Difficulty';
import { exercisesAPI } from '@/services/exercisesService';
import { useExercise } from '../ExerciseContent';

const Parameters = () => {
  const [selectedParameter, setSelectedParameter] = useState('');
  const [parameter1, setParameter1] = useState('');
  const [parameter2, setParameter2] = useState('');
  const { setExercises } = useExercise();
  const toast = useToast();

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setSelectedParameter(e.target.value);
    setParameter1(e.target.value);
    setParameter2('');
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const data = await exercisesAPI(parameter1, parameter2);
      setExercises(data);
      toast({
        title: 'Search completed successfully!',
        status: 'success',
        duration: 2000,
        isClosable: true
      })
    } catch (error: any) {
      toast({
        title: 'Error when fetching data.',
        description: error.message,
        status: 'error',
        duration: 2000,
        isClosable: true,
      });
    }
  }

  const isButtonDisabled = !parameter1 || !parameter2;

  return (
    <Box p={4}>
      <HStack spacing={5} direction="row">
        <Select
          placeholder="Select one parameter"
          onChange={handleSelectChange}
          borderColor="blue.500"
        >
          <option value="muscle"> Muscle </option>
          <option value="type"> Type of Exercise </option>
          <option value="difficulty"> Difficulty </option>
        </Select>
        {selectedParameter === 'muscle' && (
          <Muscle setParameter1={setParameter1} setParameter2={setParameter2} />
        )}
        {selectedParameter === 'type' && (
          <TypeExercise setParameter1={setParameter1} setParameter2={setParameter2} />
        )}
        {selectedParameter === 'difficulty' && (
          <Difficulty setParameter1={setParameter1} setParameter2={setParameter2} />
        )}
        <Tooltip 
          label="Please, select the parameter before searching for data" 
          isDisabled={!isButtonDisabled}
          bg="blue.400"
          color="white"
        >
        <Button
          bg="blue.400"
          color="white"
          _hover={{ bg: 'blue.500' }}
          type="submit"
          width={'30vh'}
          onClick={handleSubmit}
          isDisabled={!parameter1 || !parameter2}

        >
          Search
        </Button>
        </Tooltip>
      </HStack>
    </Box>
  );
};

export default Parameters;
