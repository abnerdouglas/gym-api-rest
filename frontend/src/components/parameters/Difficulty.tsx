import { Select, Stack } from "@chakra-ui/react";

const Difficulty = () => {
    return (
        <Stack spacing={5} direction="row">
            <Select
                placeholder='Nível de dificuldade'
                borderColor='blue.500'
            >
                <option value='beginner'> Iniciante </option>
                <option value='intermediate'> Intermediário </option>
                <option value='expert'> Avançado </option>
            </Select>
        </Stack>
    )
}

export default Difficulty;