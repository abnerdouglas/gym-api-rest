import { useState } from 'react';
import { useRouter } from 'next/router';
import {
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  Stack,
  Heading,
  Text,
  useColorModeValue,
  useToast,
  InputRightElement,
  InputGroup,
  Select,
} from '@chakra-ui/react';
import React from 'react';
import { register } from '@/services/authService';
import { mapRole, validateCPF } from '@/utils/validateValues';
import Link from 'next/link';
import Head from 'next/head';

const Register = () => {
  const [name, setName] = useState('');
  const [cpf, setCpf] = useState('');
  const [dateOfBirth, setDateOfBirth] = useState('');
  const [role, setRole] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const router = useRouter();
  const toast = useToast();
  const [loading, setLoading] = useState(false);
  const [show, setShow] = React.useState(false);
  const handleClick = () => setShow(!show);

  const handleCPFChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 11) value = value.slice(0, 11);
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d)/, '$1.$2');
    value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
    setCpf(value);
  };

  const validateCPFFormat = (cpf: string) => {
    const cpfFormat = /^\d{3}\.\d{3}\.\d{3}-\d{2}$/;
    return cpfFormat.test(cpf);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    if (!validateCPFFormat(cpf)) {
      toast({
        title: 'Invalid CPF format.',
        description: 'Please, type the CPF in format xxx.xxx.xxx-xx.',
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
      setLoading(false);
      return;
    }
    if (!validateCPF(cpf)) {
      toast({
        title: 'Invalid CPF.',
        description: 'Please, type one valid CPF.',
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
      setLoading(false);
      return;
    }
    
    try {
      const mappedRole = mapRole(role);
      await register(name, cpf, dateOfBirth, email, password, mappedRole);

      toast({
        title: 'Registered successfully.',
        status: 'success',
        duration: 5000,
        isClosable: true,
      });
      router.push('/login');
    } catch (error: any) {
      toast({
        title: 'Error in registering your account.',
        description: error.message,
        status: 'error',
        duration: 5000,
        isClosable: true,
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box
      minH="100vh"
      display="flex"
      alignItems="center"
      justifyContent="center"
      bg={useColorModeValue('gray.50', 'gray.800')}
      px={6}
    >
      <Head>
        <title>Register</title>
      </Head>

      <Stack spacing={8} mx="auto" width="100%" py={12} maxW="lg">
        <Stack align="center">
        <Heading fontSize="4xl">Register</Heading>
          <Text fontSize="lg" color="gray.600">
            Register using the information below:
          </Text>
        </Stack>
        <Box
          rounded="lg"
          bg={useColorModeValue('white', 'gray.700')}
          boxShadow="lg"
          p={8}
          width="100%" 
          maxWidth="600vh" 
        >
          <form onSubmit={handleSubmit}>
            <Stack spacing={4}>

              <FormControl id="name">
                <FormLabel>Name</FormLabel>
                <Input
                  type="text"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  required
                  placeholder='Type your full name'
                />
              </FormControl>

              <FormControl id="cpf">
                <FormLabel>CPF</FormLabel>
                <Input
                  type="text"
                  value={cpf}
                  onChange={handleCPFChange}
                  required
                  placeholder='999.999.999-99'
                />
              </FormControl>

              <FormControl id="dateOfBirth">
                <FormLabel>Date of Birth</FormLabel>
                <Input
                  type="date"
                  value={dateOfBirth}
                  onChange={(e) => setDateOfBirth(e.target.value)}
                  required
                  placeholder='Type your date of birth'
                />
              </FormControl>

              <FormControl id="email">
                <FormLabel>Email</FormLabel>
                <Input
                  type="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                  placeholder='Type your e-mail'
                />
              </FormControl>

              <FormControl id="password">
                <FormLabel>Password</FormLabel>
                <InputGroup size='md'>
                  <Input
                    type={show ? 'text' : 'password'}
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    placeholder='Type your password'
                  />
                  <InputRightElement width='3rem'>
                    <Button h='2.5rem' size='sm' onClick={handleClick}>
                      {show ? 'Hide' : 'Show'}
                    </Button>
                  </InputRightElement>
                </InputGroup>
              </FormControl>

              <FormControl id="role">
                <FormLabel>Role</FormLabel>
                  <Select 
                    placeholder='Selecione uma função'
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                    >
                   <option value='admin'> Admin </option>
                   <option value='user'> User </option>
                 </Select>
              </FormControl>

              <Stack spacing={10}>
                <Button
                  isLoading={loading}
                  bg="blue.400"
                  color="white"
                  _hover={{
                    bg: 'blue.500',
                  }}
                  type="submit"
                >
                  Register
                </Button>
              </Stack>

              <Stack spacing={10}>
                <Link href="/login">
                  Already have one account? Click here to log-in
                </Link>
              </Stack>

            </Stack>
          </form>
        </Box>
      </Stack>
    </Box>
  );
};

export default Register;
