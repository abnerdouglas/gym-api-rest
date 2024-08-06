import { Select, Stack } from "@chakra-ui/react";

interface MuscleProps {
    setParameter1: React.Dispatch<React.SetStateAction<string>>;
    setParameter2: React.Dispatch<React.SetStateAction<string>>;
}

const Muscle: React.FC<MuscleProps> = ({ setParameter1, setParameter2 }) => {

    const handleChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setParameter2(e.target.value);
    };

    return (
        <Stack spacing={5} direction="row">
            <Select
                placeholder='Grupo Muscular'
                borderColor='blue.500'
                onChange={handleChange}
            >
                <option value={'abdominals'}> Abdominais </option>
                <option value={'abductors'}> Abduções </option>
                <option value={'adductors'}> Adutores </option>
                <option value={'biceps'}> Bíceps </option>
                <option value={'triceps'}> Tríceps </option>
                <option value={'calves'}> Panturilhas </option>
                <option value={'chest'}> Peitoral </option>
                <option value={'forearms'}> Antebraços </option>
                <option value={'glutes'}> Glúteos </option>
                <option value={'hamstrings'}> Posterior de Coxa </option>
                <option value={'lats'}> Latíssimo do Dorso </option>
                <option value={'lower_back'}> Parte Baixa das Costas </option>
                <option value={'middle_back'}> Parte do Meio das Costas </option>
                <option value={'neck'}> Pescoço </option>
                <option value={'quadriceps'}> Quadríceps </option>
                <option value={'traps'}> Trapézio </option>
            </Select>
        </Stack>
    )
}

export default Muscle;