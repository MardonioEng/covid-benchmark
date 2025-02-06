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

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
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
                    <label htmlFor="startDate" className="form-label">Data Inicial</label>
                    <input
                        type="date"
                        className="form-control"
                        id="startDate"
                        name="startDate"
                        value={formData.startDate}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="mb-3">
                    <label htmlFor="endDate" className="form-label">Data Final</label>
                    <input
                        type="date"
                        className="form-control"
                        id="endDate"
                        name="endDate"
                        value={formData.endDate}
                        onChange={handleChange}
                        required
                    />
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
