'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import axios from 'axios';

const BenchmarkForm = () => {
    const router = useRouter();
    const [formData, setFormData] = useState({
        name: '',
        country1: '',
        country2: '',
        startDate: '',
        endDate: ''
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [success, setSuccess] = useState(false);
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const validationErrors = validateDates(formData);
        
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        setErrors({});
        setLoading(true);
        setError(null);
        setSuccess(false);

        try {
            const response = await axios.post('/api/benchmarks', formData);
            
            if (response.data) {
                setSuccess(true);
                setTimeout(() => {
                    router.push('/');
                    router.refresh();
                }, 1000);
            }
        } catch (err) {
            console.error('Erro detalhado:', err);
            setError(
                err.response?.data?.error || 
                err.message || 
                'Ocorreu um erro ao salvar. Por favor, tente novamente.'
            );
            if (err.response?.status === 500 && err.response?.data?.saved) {
                setTimeout(() => {
                    router.push('/');
                    router.refresh();
                }, 2000);
            }
        } finally {
            setLoading(false);
        }
    };

    const validateDates = (data) => {
        const errors = {};
        const minDate = new Date('2020-01-22');
        const maxDate = new Date('2023-03-09');
        const startDate = new Date(data.startDate);
        const endDate = new Date(data.endDate);
 
        if (startDate < minDate) {
            errors.startDate = 'A data inicial não pode ser anterior a 22/01/2020';
        }
 
        if (endDate > maxDate) {
            errors.endDate = 'A data final não pode ser posterior a 09/03/2023';
        }
 
        if (startDate > endDate) {
            errors.dateRange = 'A data inicial não pode ser maior que a data final';
        }
 
        return errors;
    };

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Cadastrar Novo Benchmark</h2>
            
            {error && (
                <div className="alert alert-danger" role="alert">
                    {error}
                </div>
            )}

            {success && (
                <div className="alert alert-success" role="alert">
                    Benchmark salvo com sucesso!
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="name" className="form-label">Nome</label>
                    <input
                        type="text"
                        className="form-control"
                        id="name"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label htmlFor="country1" className="form-label">País 1</label>
                    <input
                        type="text"
                        className="form-control"
                        id="country1"
                        name="country1"
                        value={formData.country1}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label htmlFor="country2" className="form-label">País 2</label>
                    <input
                        type="text"
                        className="form-control"
                        id="country2"
                        name="country2"
                        value={formData.countr2}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label htmlFor="startDate" className="form-label">Data Início</label>
                    <input
                        type="date"
                        className={`form-control ${errors.startDate || errors.dateRange ? 'is-invalid' : ''}`}
                        id="startDate"
                        name="startDate"
                        value={formData.startDate}
                        onChange={handleChange}
                        min="2020-01-22"
                        max="2023-03-09"
                        required
                    />
                    {errors.startDate && (
                        <div className="invalid-feedback">{errors.startDate}</div>
                    )}
                    {errors.dateRange && (
                        <div className="invalid-feedback">{errors.dateRange}</div>
                    )}
                </div>

                <div className="mb-3">
                    <label htmlFor="endDate" className="form-label">Data Fim</label>
                    <input
                        type="date"
                        className={`form-control ${errors.endDate || errors.dateRange ? 'is-invalid' : ''}`}
                        id="endDate"
                        name="endDate"
                        value={formData.endDate}
                        onChange={handleChange}
                        min="2020-01-22"
                        max="2023-03-09"
                        required
                    />
                    {errors.endDate && (
                        <div className="invalid-feedback">{errors.endDate}</div>
                    )}
                </div>

                <div className="mb-3">
                    <button 
                        type="submit" 
                        className="btn btn-primary me-2"
                        disabled={loading}
                    >
                        {loading ? (
                            <>
                                <span className="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                                Salvando...
                            </>
                        ) : 'Salvar'}
                    </button>
                    <button 
                        type="button" 
                        className="btn btn-secondary"
                        onClick={() => router.push('/')}
                        disabled={loading}
                    >
                        Cancelar
                    </button>
                </div>
            </form>
        </div>
    );
};

export default BenchmarkForm;
