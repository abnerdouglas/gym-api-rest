import { Box, Heading, Text, VStack } from '@chakra-ui/react';
import { useExercise } from './ExerciseContent';

const CardTemplate = () => {
  const { exercises } = useExercise();
  
  return (
    <Box p={4}>
      <VStack mt={5} spacing={4}>
        {exercises.length > 0 ? (
          exercises.map((exercise, index) => (
            <Box key={index} p={4} borderWidth="1px" borderColor={'black'} borderRadius="lg" w="full">

              <Heading size={'md'}> {exercise.name} </Heading>

              <Text pt={3} fontSize="large">
                <Box as="span" fontWeight="bold">Muscle:</Box> {exercise.muscle}
              </Text>

              <Text pt={3} fontSize="large">
                <Box as="span" fontWeight="bold">Type of Exercise:</Box> {exercise.type}
              </Text>

              <Text pt={3} fontSize="large">
                <Box as="span" fontWeight="bold">Difficulty:</Box> {exercise.difficulty}
              </Text>

              <Text pt={3} fontSize="large">
                <Box as="span" fontWeight="bold">Equipment:</Box> {exercise.equipment}
              </Text>

              <Text pt={3} fontSize="large">
                <Box as="span" fontWeight="bold">Instructions:</Box> {exercise.instructions || 'No instructions provided'}
              </Text>

            </Box>
          ))
        ) : (
          <Text>There are no exercises selected yet to show here</Text>
        )}
      </VStack>
    </Box>
  );
};

export default CardTemplate;