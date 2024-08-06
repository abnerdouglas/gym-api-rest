import { Select, Stack } from "@chakra-ui/react";

const TypeExercise = () => {
    return (
        <Stack spacing={5} direction="row">
            <Select
                placeholder='Tipo de Exercício'
                borderColor='blue.500'
            >
                <option value='cardio'> Cardio </option>
                <option value='olympic_weightlifting'> Levantamento de Peso Olímpico </option>
                <option value='plyometrics'> Pilometria </option>
                <option value='powerlifting'> Levantamento de Peso </option>
                <option value='strength'> Força </option>
                <option value='stretchingt'> Alongamento </option>
                <option value='strongman'> Strongman </option>
            </Select>
        </Stack>
    )
}

export default TypeExercise;